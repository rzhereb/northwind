package com.northwind.northwindrestapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.northwind.northwindrestapi.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderPatchDTO implements PatchDtoToEntityMapper<Order> {

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime orderDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime requiredDate;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime shippedDate;
  private double freight;
  private String shipName;
  private String shipAddress;
  private String shipCity;
  private String shipRegion;
  private String shipPostalCode;
  private String shipCountry;
  private String customer;
  private int employee;

  @Override
  public void mapFromPatch(Order entity) {
    if (this.getOrderDate() != null) {
      entity.setOrderDate(this.getOrderDate());
    }
    if (this.getRequiredDate() != null) {
      entity.setRequiredDate(this.getRequiredDate());
    }
    if (this.getShippedDate() != null) {
      entity.setShippedDate(this.getShippedDate());
    }
    if (this.getFreight() != 0) {
      entity.setFreight(this.getFreight());
    }
    if (this.getShipName() != null) {
      entity.setShipName(this.getShipName());
    }
    if (this.getShipAddress() != null) {
      entity.setShipAddress(this.getShipAddress());
    }
    if (this.getShipCity() != null) {
      entity.setShipCity(this.getShipCity());
    }
    if (this.getShipCountry() != null) {
      entity.setShipCountry(this.getShipCountry());
    }
    if (this.getShipPostalCode() != null) {
      entity.setShipPostalCode(this.getShipPostalCode());
    }
    if (this.getShipRegion() != null) {
      entity.setShipRegion(this.getShipRegion());
    }
  }
}
