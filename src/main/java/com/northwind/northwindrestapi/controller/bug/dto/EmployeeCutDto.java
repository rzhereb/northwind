package com.northwind.northwindrestapi.controller.bug.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.northwind.northwindrestapi.entity.Employee;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EmployeeCutDto {

  private int id;
  private String firstName;
  private String lastName;
  private String title;
  private String titleOfCourtesy;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime hireDate;
  private String address;
  private String postalCode;
  private String country;
  private String notes;
  private String photoPath;
  private double salary;

  public EmployeeCutDto(Employee employee) {
    this.id = employee.getId();
    this.firstName = employee.getFirstName();
    this.lastName = employee.getLastName();
    this.title = employee.getTitle();
    this.titleOfCourtesy = employee.getTitleOfCourtesy();
    this.hireDate = employee.getHireDate();
    this.address = employee.getAddress();
    this.postalCode = employee.getPostalCode();
    this.country = employee.getCountry();
    this.notes = employee.getNotes();
    this.photoPath = employee.getPhotoPath();
    this.salary = employee.getSalary();

  }
}
