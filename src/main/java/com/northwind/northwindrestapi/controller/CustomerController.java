package com.northwind.northwindrestapi.controller;

import com.northwind.northwindrestapi.dto.CustomerPatchDTO;
import com.northwind.northwindrestapi.entity.Customer;
import com.northwind.northwindrestapi.service.interfaces.ICustomerService;
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
@RequestMapping(value = "/api/v2/customer")
@AllArgsConstructor
public class CustomerController {

  private ICustomerService customerService;

  @GetMapping
  public List<Customer> getAllCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping(value = "/{id}")
  public Customer getCustomer(@PathVariable String id) {
    return customerService.getCustomer(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Customer saveCustomer(@RequestBody Customer customer) {
    return customerService.create(customer);
  }

  @PutMapping(value = "/{id}")
  public Customer updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
    return customerService.update(id, customer);
  }

  @PatchMapping(value = "/{id}")
  public Customer patch(@PathVariable String id, @RequestBody CustomerPatchDTO customer) {
    return customerService.patch(id, customer);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable String id) {
    customerService.delete(id);
  }

}
