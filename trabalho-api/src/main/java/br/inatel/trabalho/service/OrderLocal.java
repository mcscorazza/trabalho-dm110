package br.inatel.trabalho.service;

import jakarta.ejb.Local;
import java.util.List;
import br.inatel.trabalho.api.OrderTO;

@Local
public interface OrderLocal {
  OrderTO create(OrderTO to);
  OrderTO find(String orderCode);
  List<OrderTO> listAll();
  OrderTO update(OrderTO to);
}
