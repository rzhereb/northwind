package com.northwind.northwindrestapi.service;

import com.northwind.northwindrestapi.dao.EmployeeRepository;
import com.northwind.northwindrestapi.dto.EmployeePatchDTO;
import com.northwind.northwindrestapi.entity.Employee;
import com.northwind.northwindrestapi.service.interfaces.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService implements IEmployeeService {

  private EmployeeRepository employeeRepository;

  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee getEmployee(int id) {
    return employeeRepository.findById(id).orElse(null);
  }

  @Override
  public Employee create(Employee employee, MultipartFile file, int reportsToId) {
    if (file != null && !file.isEmpty()) {
      try {
        employee.setPhoto(file.getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    Employee reportsTo = this.getEmployee(reportsToId);
    employee.setReportsTo(reportsTo);
    return employeeRepository.saveAndFlush(employee);
  }

  @Override
  public Employee update(int id, Employee employee) {
    if (employeeRepository.existsById(id)) {
      employee.setId(id);
      return employeeRepository.saveAndFlush(employee);
    }
    return null;
  }

  @Override
  public void delete(int id) {
    employeeRepository.deleteById(id);
  }

  @Override
  public Employee patch(int id, EmployeePatchDTO employeePatchDTO) {
    final Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, String.format("No Employee with id %s found", id)));
    employeePatchDTO.mapFromPatch(employee);

    final Employee employeeReportsTo = this.getEmployee(employeePatchDTO.getReportsTo());
    if (employeeReportsTo != null) {
      employee.setReportsTo(employeeReportsTo);
    }

    return employeeRepository.saveAndFlush(employee);
  }
}
