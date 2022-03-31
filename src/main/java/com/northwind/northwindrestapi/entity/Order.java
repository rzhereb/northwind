package com.northwind.northwindrestapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Orders")
@NoArgsConstructor
@Data
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "OrderID")
  @ApiModelProperty(hidden = true)
  private int id;

  @Column(name = "OrderDate")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(example = "2022-03-30 11:11:11")
  private LocalDateTime orderDate;

  @Column(name = "RequiredDate")
  @ApiModelProperty(example = "2022-03-30 11:11:11")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime requiredDate;

  @Column(name = "ShippedDate")
  @ApiModelProperty(example = "2022-03-30 11:11:11")
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
