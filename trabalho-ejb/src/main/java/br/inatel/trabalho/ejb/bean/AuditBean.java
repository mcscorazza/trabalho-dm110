package br.inatel.trabalho.ejb.bean;

import java.time.LocalDateTime;

import br.inatel.trabalho.ejb.entity.AuditEntity;
import br.inatel.trabalho.ejb.service.AuditLocal;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
@Local(AuditLocal.class)
public class AuditBean implements AuditLocal {
  @PersistenceContext(unitName="auditPU")
  private EntityManager em;
  @Override
  public void record(String code, String operation, LocalDateTime ts) {
    AuditEntity a = AuditEntity.builder()
      .recordCode(code)
      .operation(operation)
      .timestamp(ts)
      .build();
    em.persist(a);
  }
}
