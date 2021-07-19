package com.northwind.northwindrestapi.controller.bug.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.northwind.northwindrestapi.entity.Order;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderCutDto {

  private int id;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime orderDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime requiredDate;
  private String shipName;
  private String shipAddress;
  private String shipCity;
  private String shipPostalCode;
  private String shipCountry;

  public OrderCutDto(Order order) {
    this.id = order.getId();
    this.orderDate = order.getOrderDate();
    this.requiredDate = order.getRequiredDate();
    this.shipName = order.getShipName();
    this.shipAddress = order.getShipAddress();
    this.shipCity = order.getShipCity();
    this.shipPostalCode = order.getShipPostalCode();
    this.shipCountry = order.getShipCountry();
  }
}
