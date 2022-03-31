package com.northwind.northwindrestapi.controller.bug.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.northwind.northwindrestapi.dto.OrderDTO;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderCutDto {

  private final int id;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime orderDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private final LocalDateTime requiredDate;
  private final String shipName;
  private final String shipAddress;
  private final String shipCity;
  private final String shipPostalCode;
  private final String shipCountry;

  public OrderCutDto(OrderDTO order) {
    this.id = order.getOrder().getId();
    this.orderDate = order.getOrder().getOrderDate();
    this.requiredDate = order.getOrder().getRequiredDate();
    this.shipName = order.getOrder().getShipName();
    this.shipAddress = order.getOrder().getShipAddress();
    this.shipCity = order.getOrder().getShipCity();
    this.shipPostalCode = order.getOrder().getShipPostalCode();
    this.shipCountry = order.getOrder().getShipCountry();
  }
}
