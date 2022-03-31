package com.northwind.northwindrestapi.controller;

import com.northwind.northwindrestapi.dto.OrderDTO;
import com.northwind.northwindrestapi.dto.OrderPatchDTO;
import com.northwind.northwindrestapi.entity.Order;
import com.northwind.northwindrestapi.service.interfaces.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/order")
@AllArgsConstructor
public class OrderController {

  private IOrderService orderService;

  @GetMapping
  public List<OrderDTO> getAllOrders() {
    return orderService.getAllOrders();
  }

  @GetMapping(value = "/{id}")
  public OrderDTO getOrder(@PathVariable int id) {
    return orderService.getOrder(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Order saveOrder(@RequestBody OrderDTO order) {
    return orderService.create(order);
  }

  @PutMapping(value = "/{id}")
  public Order updateOrder(@PathVariable int id, @RequestBody Order order) {
    return orderService.update(id, order);
  }

  @PatchMapping(value = "/{id}")
  public Order patch(@PathVariable int id, @RequestBody OrderPatchDTO order) {
    return orderService.patch(id, order);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable int id) {
    orderService.delete(id);
  }

}
