package com.northwind.northwindrestapi.service.interfaces;

import com.northwind.northwindrestapi.dto.CustomerCreateDTO;
import com.northwind.northwindrestapi.dto.CustomerPatchDTO;
import com.northwind.northwindrestapi.entity.Customer;

import java.util.List;

public interface ICustomerService {

  List<Customer> getAllCustomers();

  Customer getCustomer(String id);

  Customer create(CustomerCreateDTO customer);

  Customer update(String id, CustomerCreateDTO customer);

  void delete(String id);

  Customer patch(String id, CustomerPatchDTO customer);
}
