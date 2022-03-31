package com.northwind.northwindrestapi.service;

import com.northwind.northwindrestapi.dao.CustomerRepository;
import com.northwind.northwindrestapi.dto.CustomerCreateDTO;
import com.northwind.northwindrestapi.dto.CustomerPatchDTO;
import com.northwind.northwindrestapi.entity.Customer;
import com.northwind.northwindrestapi.mapper.CustomerMapper;
import com.northwind.northwindrestapi.service.interfaces.ICustomerService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

  private CustomerRepository customerRepository;
  private CustomerMapper customerMapper;

  @Override
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Customer getCustomer(String id) {
    return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @Override
  public Customer create(CustomerCreateDTO customer) {
    String id = RandomStringUtils.random(5, true, false);
    Customer entity = customerMapper.createDtoToEntity(customer);
    entity.setId(id);
    return customerRepository.saveAndFlush(entity);
  }

  @Override
  public Customer update(String id, CustomerCreateDTO customer) {
    if (customerRepository.existsById(id)) {
      Customer entity = customerMapper.createDtoToEntity(customer);
      entity.setId(id);
      return customerRepository.saveAndFlush(entity);
    }
    else
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  @Override
  public void delete(String id) {
    if (!customerRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
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
