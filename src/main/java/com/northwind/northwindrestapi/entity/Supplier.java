package com.northwind.northwindrestapi.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Suppliers")
@NoArgsConstructor
@Data
public class Supplier {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SupplierID")
  private int id;
  @Column(name = "CompanyName", nullable = false)
  @ApiModelProperty(required = true)
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
  @Lob
  @Column(name = "HomePage", length = 512)
  private String homePage;

}
