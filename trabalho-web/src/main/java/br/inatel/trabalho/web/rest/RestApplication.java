package br.inatel.trabalho.web.rest;
import java.util.HashSet;
import java.util.Set;

import br.inatel.trabalho.impl.resource.AuditResource;
import br.inatel.trabalho.impl.resource.OrderResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(OrderResource.class); // Registra manualmente o recurso do JAR
        resources.add(AuditResource.class); // Registra manualmente o recurso do JAR
        return resources;
    }
}
