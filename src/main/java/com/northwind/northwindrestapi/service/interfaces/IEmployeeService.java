package com.northwind.northwindrestapi.service.interfaces;

import com.northwind.northwindrestapi.dto.EmployeePatchDTO;
import com.northwind.northwindrestapi.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmployeeService {

  List<Employee> getAllEmployees();

  Employee getEmployee(int id);

  Employee create(Employee employee, MultipartFile file, int reportsToId);

  Employee update(int id, Employee employee);

  void delete(int id);

  Employee patch(int id, EmployeePatchDTO employee);
}
