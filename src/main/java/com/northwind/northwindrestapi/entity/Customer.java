package com.northwind.northwindrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

  @Id
  @Column(name = "CustomerID")
  private String id;

  @Column(name = "CompanyName", nullable = false)
  private String companyName;

  @Column(name = "ContactName")
  private String contactName;

  @Column(name = "ContactTitle")
  private String contactTitle;

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

  @Column(name = "Phone")
  private String phone;

  @Column(name = "Fax")
  private String fax;

}
