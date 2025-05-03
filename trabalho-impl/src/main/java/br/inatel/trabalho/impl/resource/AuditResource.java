package br.inatel.trabalho.impl.resource;

import java.util.List;

import br.inatel.trabalho.ejb.entity.AuditEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Stateless
@Path("/audit")
@Produces(MediaType.APPLICATION_JSON)
public class AuditResource {

    @PersistenceContext(unitName = "auditPU")
    private EntityManager em;

    @GET
    public List<AuditEntity> listAll() {
        return em.createQuery("SELECT a FROM AuditEntity a ORDER BY a.timestamp DESC", AuditEntity.class)
                 .getResultList();
    }
}