package com.northwind.northwindrestapi.controller;

import com.northwind.northwindrestapi.dto.EmployeePatchDTO;
import com.northwind.northwindrestapi.entity.Employee;
import com.northwind.northwindrestapi.service.interfaces.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/employee")
@AllArgsConstructor
public class EmployeeController {

  private IEmployeeService employeeService;

  @GetMapping
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }

  @GetMapping(value = "/{id}")
  public Employee getEmployee(@PathVariable int id) {
    return employeeService.getEmployee(id);
  }

  @PostMapping(value = "/reportsTo/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Employee saveEmployee(@ModelAttribute Employee employee, @PathVariable(value = "id") int reportsToId,
      @RequestParam(required = false) MultipartFile file) {
    return employeeService.create(employee, file, reportsToId);
  }

  @PutMapping(value = "/{id}")
  public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
    return employeeService.update(id, employee);
  }

  @PatchMapping(value = "/{id}")
  public Employee patch(@PathVariable int id, @RequestBody EmployeePatchDTO employee) {
    return employeeService.patch(id, employee);
  }


  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteEmployee(@PathVariable int id) {
    employeeService.delete(id);
  }

}
