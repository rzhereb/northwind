package com.northwind.northwindrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
@NoArgsConstructor
@Data
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ProductID")
  private int id;

  @Column(name = "ProductName")
  private String name;

  @Column(name = "QuantityPerUnit")
  private String quantityPerUnit;

  @Column(name = "UnitPrice")
  private double unitPrice;

  @Column(name = "UnitsInStock")
  private int unitsInStock;

  @Column(name = "UnitsOnOrder")
  private int unitsOnOrder;

  @Column(name = "ReorderLevel")
  private int reorderLevel;

  @ManyToOne(targetEntity = Category.class)
  @JoinColumn(name = "CategoryID")
  @JsonIgnore
  private Category category;

}
