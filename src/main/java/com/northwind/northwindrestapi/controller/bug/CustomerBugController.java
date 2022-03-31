package com.northwind.northwindrestapi.controller.bug;

import com.northwind.northwindrestapi.controller.bug.dto.CustomerCutDto;
import com.northwind.northwindrestapi.dto.CustomerCreateDTO;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/customer")
@AllArgsConstructor
public class CustomerBugController {

  private ICustomerService customerService;

  @GetMapping
  public List<CustomerCutDto> getAllCustomers() {
    return customerService.getAllCustomers()
        .stream().map(CustomerCutDto::new).collect(Collectors.toList());
  }

  @GetMapping(value = "/{id}")
  public CustomerCutDto getCustomer(@PathVariable String id) {
    return Optional.of(customerService.getCustomer(id)).map(CustomerCutDto::new).get();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
  public Customer saveCustomer(@RequestBody CustomerCreateDTO customer) {
    return customerService.create(customer);
  }

  @PutMapping(value = "/{id}")
  public Customer updateCustomer(@PathVariable String id, @RequestBody CustomerCreateDTO customer) {
    return customerService.update(id, customer);
  }

  @PatchMapping(value = "/{id}")
  public Customer patch(@PathVariable String id, @RequestBody CustomerPatchDTO customer) {
    return customerService.patch(id, customer);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCustomer(@PathVariable String id) {

  }


}
