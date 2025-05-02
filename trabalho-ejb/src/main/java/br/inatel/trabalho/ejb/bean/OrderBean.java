package br.inatel.trabalho.ejb.bean;

import br.inatel.trabalho.api.OrderTO;
import br.inatel.trabalho.ejb.entity.OrderEntity;
import br.inatel.trabalho.service.OrderLocal;
import jakarta.annotation.Resource;
import jakarta.ejb.*;
import jakarta.persistence.*;
import jakarta.jms.*;
import jakarta.jms.Queue;

import java.util.*;
import java.time.LocalDateTime;
import jakarta.json.*;

@Stateless
@Local(OrderLocal.class)
public class OrderBean implements OrderLocal {
  @PersistenceContext(unitName="trabalhoPU")
  private EntityManager em;

  @Resource(lookup="java:/ConnectionFactory")
  private ConnectionFactory cf;

  @Resource(lookup="java:/jms/queue/auditQueue")
  private Queue auditQueue;

  @Override
  public OrderTO create(OrderTO to) {
    OrderEntity e = toEntity(to);
    em.persist(e);
    sendAudit(e.getOrderCode(), "create");
    return toTO(e);
  }
  @Override
  public OrderTO find(String orderCode) {
    OrderEntity e = em.find(OrderEntity.class, orderCode);
    return e==null?null:toTO(e);
  }
  @Override
  public List<OrderTO> listAll() {
    List<OrderEntity> list = em.createQuery("SELECT e FROM OrderEntity e", OrderEntity.class).getResultList();
    List<OrderTO> out = new ArrayList<>();
    list.forEach(e -> out.add(toTO(e)));
    return out;
  }
  @Override
  public OrderTO update(OrderTO to) {
    OrderEntity e = em.merge(toEntity(to));
    sendAudit(e.getOrderCode(), "update");
    return toTO(e);
  }

  private void sendAudit(String code, String op) {
    try (JMSContext ctx = cf.createContext()) {
      JsonObject msg = Json.createObjectBuilder()
        .add("recordCode", code)
        .add("operation", op)
        .add("timestamp", LocalDateTime.now().toString())
        .build();
      ctx.createProducer().send(auditQueue, msg.toString());
    }
  }

  // Métodos de conversão
  private OrderEntity toEntity(OrderTO to) {
    return OrderEntity.builder()
        .orderCode(to.getOrderCode())
        .productCode(to.getProductCode())
        .cpf(to.getCpf())
        .amount(to.getAmount())
        .dateOrder(to.getDateOrder())
        .orderValue(to.getOrderValue())
        .build();
}

private OrderTO toTO(OrderEntity e) {
  return OrderTO.builder()
      .orderCode(e.getOrderCode())
      .productCode(e.getProductCode())
      .cpf(e.getCpf())
      .amount(e.getAmount())
      .dateOrder(e.getDateOrder())
      .orderValue(e.getOrderValue())
      .build();
}

}
