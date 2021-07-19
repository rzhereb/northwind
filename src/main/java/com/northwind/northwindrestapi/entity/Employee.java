package com.northwind.northwindrestapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Employees")
@NoArgsConstructor
@Data
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EmployeeID")
  private int id;

  @Column(name = "FirstName", nullable = false)
  private String firstName;

  @Column(name = "LastName", nullable = false)
  private String lastName;

  @Column(name = "Title")
  private String title;

  @Column(name = "TitleOfCourtesy")
  private String titleOfCourtesy;

  @Column(name = "BirthDate")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime birthDate;

  @Column(name = "HireDate")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime hireDate;

  @Column(name = "Address")
  private String address;

  @Column(name = "City")
  private String city;

  @Column(name = "Region")
  private String region;

  @Column(name = "PostalCode")
  private String postalCode;

  @Column(name = "Country")
  private String country;

  @Column(name = "HomePhone")
  private String homePhone;

  @Column(name = "Extension")
  private String extension;

  @Lob
  @Column(name = "Notes", length = 512, nullable = false)
  private String notes;

  @Lob
  @Column(name = "Photo")
  private byte[] photo;


  @Column(name = "PhotoPath")
  private String photoPath;

  @Column(name = "Salary")
  private double salary;

  @ManyToOne
  @JoinColumn(name = "ReportsTo")
  @JsonIgnore
  private Employee reportsTo;
}
