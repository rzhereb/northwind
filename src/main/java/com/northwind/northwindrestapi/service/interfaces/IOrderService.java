package com.northwind.northwindrestapi.service.interfaces;

import com.northwind.northwindrestapi.dto.OrderDTO;
import com.northwind.northwindrestapi.dto.OrderPatchDTO;
import com.northwind.northwindrestapi.entity.Order;

import java.util.List;

public interface IOrderService {

  List<OrderDTO> getAllOrders();

  OrderDTO getOrder(int id);

  Order create(OrderDTO order);

  Order update(int id, Order order);

  void delete(int id);

  Order patch(int id, OrderPatchDTO order);
}
