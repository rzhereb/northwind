package com.northwind.northwindrestapi.service.interfaces;

import com.northwind.northwindrestapi.dto.OrderCreateDTO;
import com.northwind.northwindrestapi.dto.OrderPatchDTO;
import com.northwind.northwindrestapi.entity.Order;

import java.util.List;

public interface IOrderService {

  List<Order> getAllOrders();

  Order getOrder(int id);

  Order create(OrderCreateDTO order);

  Order update(int id, Order order);

  void delete(int id);

  Order patch(int id, OrderPatchDTO order);
}
