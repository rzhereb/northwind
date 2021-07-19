package com.northwind.northwindrestapi.dao;

import com.northwind.northwindrestapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
