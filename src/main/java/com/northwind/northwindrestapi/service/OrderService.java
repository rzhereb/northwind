package com.northwind.northwindrestapi.service;

import com.northwind.northwindrestapi.dao.OrderRepository;
import com.northwind.northwindrestapi.dto.OrderDTO;
import com.northwind.northwindrestapi.dto.OrderPatchDTO;
import com.northwind.northwindrestapi.entity.Customer;
import com.northwind.northwindrestapi.entity.Employee;
import com.northwind.northwindrestapi.entity.Order;
import com.northwind.northwindrestapi.mapper.OrderMapper;
import com.northwind.northwindrestapi.service.interfaces.ICustomerService;
import com.northwind.northwindrestapi.service.interfaces.IEmployeeService;
import com.northwind.northwindrestapi.service.interfaces.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {

  private OrderRepository orderRepository;
  private IEmployeeService employeeService;
  private ICustomerService customerService;
  private OrderMapper orderMapper;

  @Override
  public List<OrderDTO> getAllOrders() {
    return orderRepository.findAll()
            .stream()
            .map(orderMapper::entityToDto)
            .collect(Collectors.toList());
  }

  @Override
  public OrderDTO getOrder(int id) {
    Order order = orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return orderMapper.entityToDto(order);
  }

  @Override
  public Order create(OrderDTO order) {
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
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public void delete(int id) {
    if (!orderRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
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
