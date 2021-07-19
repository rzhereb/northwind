package com.northwind.northwindrestapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.northwind.northwindrestapi.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class EmployeePatchDTO implements PatchDtoToEntityMapper<Employee> {

  private String firstName;
  private String lastName;
  private String title;
  private String titleOfCourtesy;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime birthDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime hireDate;
  private String address;
  private String city;
  private String region;
  private String postalCode;
  private String country;
  private String homePhone;
  private String extension;
  private String notes;
  private String photoPath;
  private double salary;
  private int reportsTo;

  @Override
  public void mapFromPatch(Employee entity) {
    if (this.getFirstName() != null) {
      entity.setFirstName(this.getFirstName());
    }
    if (this.getLastName() != null) {
      entity.setLastName(this.getLastName());
    }
    if (this.getTitle() != null) {
      entity.setTitle(this.getTitle());
    }
    if (this.getTitleOfCourtesy() != null) {
      entity.setTitleOfCourtesy(this.getTitleOfCourtesy());
    }
    if (this.getBirthDate() != null) {
      entity.setBirthDate(this.getBirthDate());
    }
    if (this.getHireDate() != null) {
      entity.setHireDate(this.getHireDate());
    }
    if (this.getAddress() != null) {
      entity.setAddress(this.getAddress());
    }
    if (this.getCity() != null) {
      entity.setCity(this.getCity());
    }
    if (this.getRegion() != null) {
      entity.setRegion(this.getPostalCode());
    }
    if (this.getCountry() != null) {
      entity.setCountry(this.getCountry());
    }
    if (this.getHomePhone() != null) {
      entity.setHomePhone(this.getHomePhone());
    }
    if (this.getExtension() != null) {
      entity.setExtension(this.getExtension());
    }
    if (this.getNotes() != null) {
      entity.setNotes(this.getNotes());
    }
    if (this.getPhotoPath() != null) {
      entity.setPhotoPath(this.getPhotoPath());
    }
    if (this.getSalary() != 0) {
      entity.setSalary(this.getSalary());
    }
  }
}
