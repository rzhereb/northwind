package com.northwind.northwindrestapi.service;

import com.northwind.northwindrestapi.dao.OrderRepository;
import com.northwind.northwindrestapi.dto.OrderCreateDTO;
import com.northwind.northwindrestapi.dto.OrderPatchDTO;
import com.northwind.northwindrestapi.entity.Customer;
import com.northwind.northwindrestapi.entity.Employee;
import com.northwind.northwindrestapi.entity.Order;
import com.northwind.northwindrestapi.service.interfaces.ICustomerService;
import com.northwind.northwindrestapi.service.interfaces.IEmployeeService;
import com.northwind.northwindrestapi.service.interfaces.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {

  private OrderRepository orderRepository;
  private IEmployeeService employeeService;
  private ICustomerService customerService;

  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @Override
  public Order getOrder(int id) {
    return orderRepository.findById(id).orElse(null);
  }

  @Override
  public Order create(OrderCreateDTO order) {
    final Employee employee = employeeService.getEmployee(order.getEmployeeId());
    final Customer customer = customerService.getCustomer(order.getCustomerId());
    final Order orderToSave = order.getOrder();
    orderToSave.setCustomer(customer);
    orderToSave.setEmployee(employee);
    return orderRepository.saveAndFlush(orderToSave);
  }

  @Override
  public Order update(int id, Order order) {
    if (orderRepository.existsById(id)) {
      order.setId(id);
      return orderRepository.saveAndFlush(order);
    }
    return null;
  }

  @Override
  public void delete(int id) {
    orderRepository.deleteById(id);
  }

  @Override
  public Order patch(int id, OrderPatchDTO orderPatchDTO) {
    final Order order = orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, String.format("No Order with id %s found", id)));
    orderPatchDTO.mapFromPatch(order);

    if (orderPatchDTO.getCustomer() != null) {
      final Customer customer = customerService.getCustomer(orderPatchDTO.getCustomer());
      if (customer != null) {
        order.setCustomer(customer);
      }
    }
    final Employee employee = employeeService.getEmployee(orderPatchDTO.getEmployee());
    if (employee != null) {
      order.setEmployee(employee);
    }
    return orderRepository.saveAndFlush(order);
  }
}
