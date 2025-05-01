package br.inatel.trabalho.impl.resource;

import java.net.URI;
import java.util.List;

import br.inatel.trabalho.api.OrderTO;
import br.inatel.trabalho.service.OrderLocal;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;


@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
  @EJB private OrderLocal bean;

  @POST
  public Response create(OrderTO to, @Context UriInfo ui) {
    OrderTO out = bean.create(to);
    URI loc = ui.getAbsolutePathBuilder().path(out.getOrderCode()).build();
    return Response.created(loc).entity(out).build();
  }

  @GET public List<OrderTO> list() { return bean.listAll(); }

  @GET @Path("{code}")
  public OrderTO get(@PathParam("code") String code) {
    return bean.find(code);
  }

  @PUT @Path("{code}")
  public OrderTO update(@PathParam("code") String code, OrderTO to) {
    to.setOrderCode(code);
    return bean.update(to);
  }
}
