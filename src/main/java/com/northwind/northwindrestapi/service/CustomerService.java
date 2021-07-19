package com.northwind.northwindrestapi.service;

import com.northwind.northwindrestapi.dao.CustomerRepository;
import com.northwind.northwindrestapi.dto.CustomerPatchDTO;
import com.northwind.northwindrestapi.entity.Customer;
import com.northwind.northwindrestapi.service.interfaces.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

  private CustomerRepository customerRepository;

  @Override
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Customer getCustomer(String id) {
    return customerRepository.findById(id).orElse(null);
  }

  @Override
  public Customer create(Customer customer) {
    return customerRepository.saveAndFlush(customer);
  }

  @Override
  public Customer update(String id, Customer customer) {
    if (customerRepository.existsById(id)) {
      return customerRepository.saveAndFlush(customer);
    }
    else return null;
  }

  @Override
  public void delete(String id) {
    customerRepository.deleteById(id);
  }

  @Override
  public Customer patch(String id, CustomerPatchDTO customerPatchDTO) {
    final Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, String.format("No Customer with id %s found", id)));
    customerPatchDTO.mapFromPatch(customer);
    return customerRepository.saveAndFlush(customer);
  }
}
