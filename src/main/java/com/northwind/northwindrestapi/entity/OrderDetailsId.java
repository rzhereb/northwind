package com.northwind.northwindrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class OrderDetailsId implements Serializable {

  @Column(name = "OrderID")
  private int orderId;

  @Column(name = "ProductID")
  private int productId;

}
