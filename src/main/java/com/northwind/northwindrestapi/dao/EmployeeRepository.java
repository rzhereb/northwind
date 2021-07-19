package com.northwind.northwindrestapi.dao;

import com.northwind.northwindrestapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
