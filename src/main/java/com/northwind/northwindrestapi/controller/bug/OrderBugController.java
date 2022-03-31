package com.northwind.northwindrestapi.controller.bug;

import com.northwind.northwindrestapi.controller.bug.dto.OrderCutDto;
import com.northwind.northwindrestapi.dto.OrderDTO;
import com.northwind.northwindrestapi.dto.OrderPatchDTO;
import com.northwind.northwindrestapi.entity.Order;
import com.northwind.northwindrestapi.service.interfaces.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/order")
@AllArgsConstructor
public class OrderBugController {

  private IOrderService orderService;

  @GetMapping
  public List<OrderCutDto> getAllOrders() {
    return orderService.getAllOrders().stream().map(OrderCutDto::new).collect(Collectors.toList());
  }

  @GetMapping(value = "/{id}")
  public OrderDTO getOrder(@PathVariable int id) {
    return orderService.getOrder(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Order saveOrder(@RequestBody OrderDTO order) {
    order.getOrder().setShipRegion(null);
    order.getOrder().setFreight(0);
    order.getOrder().setRequiredDate(null);
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
