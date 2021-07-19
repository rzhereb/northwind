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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
@NoArgsConstructor
@Data
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OrderID")
  private int id;

  @Column(name = "OrderDate")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime orderDate;

  @Column(name = "RequiredDate")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime requiredDate;

  @Column(name = "ShippedDate")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime shippedDate;

  @Column(name = "Freight")
  private double freight;

  @Column(name = "ShipName")
  private String shipName;

  @Column(name = "ShipAddress")
  private String shipAddress;

  @Column(name = "ShipCity")
  private String shipCity;

  @Column(name = "ShipRegion")
  private String shipRegion;

  @Column(name = "ShipPostalCode")
  private String shipPostalCode;

  @Column(name = "ShipCountry")
  private String shipCountry;

  @ManyToOne(targetEntity = Customer.class)
  @JoinColumn(name = "CustomerID")
  @JsonIgnore
  private Customer customer;

  @ManyToOne(targetEntity = Employee.class)
  @JoinColumn(name = "EmployeeID")
  @JsonIgnore
  private Employee employee;
}
