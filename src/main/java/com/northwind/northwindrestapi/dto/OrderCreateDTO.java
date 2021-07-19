package com.northwind.northwindrestapi.dto;

import com.northwind.northwindrestapi.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCreateDTO {

  private Order order;
  private String customerId;
  private int employeeId;
}
