package br.inatel.trabalho.ejb.mdb;

import java.io.StringReader;
import java.time.LocalDateTime;

import br.inatel.trabalho.ejb.entity.AuditEntity;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MessageDriven(activationConfig = {
  @ActivationConfigProperty(propertyName="destinationType", propertyValue="jakarta.jms.Queue"),
  @ActivationConfigProperty(propertyName="destination",     propertyValue="java:/jms/queue/auditQueue")
})
public class AuditMDB implements MessageListener {
  @PersistenceContext(unitName="trabalho_dm110_pu")
  private EntityManager em;

  @Override
  public void onMessage(Message m) {
    try {
      String txt = ((TextMessage)m).getText();
      JsonObject o = Json.createReader(new StringReader(txt)).readObject();
      AuditEntity a = new AuditEntity();
      a.setRecordCode(o.getString("recordCode"));
      a.setOperation(o.getString("operation"));
      a.setTimestamp(LocalDateTime.parse(o.getString("timestamp")));
      em.persist(a);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
