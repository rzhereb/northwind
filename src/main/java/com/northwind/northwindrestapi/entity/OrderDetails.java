package com.northwind.northwindrestapi.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "Order Details")
@NoArgsConstructor
public class OrderDetails {

  @EmbeddedId
  private OrderDetailsId id;

  @Column(name = "UnitPrice", nullable = false)
  private double unitPrice;

  @Column(name = "Quantity", nullable = false)
  private int quantity;

  @Column(name = "Discount", nullable = false)
  private double discount;

  @ManyToOne
  @MapsId("orderId")
  @JoinColumn(name = "OrderID")
  private Order order;

  @ManyToOne
  @MapsId("productId")
  @JoinColumn(name = "ProductID")
  private Product product;

}
