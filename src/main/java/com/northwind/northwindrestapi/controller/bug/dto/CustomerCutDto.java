package com.northwind.northwindrestapi.controller.bug.dto;

import com.northwind.northwindrestapi.entity.Customer;
import lombok.Getter;

@Getter
public class CustomerCutDto {

  private String id;
  private String companyName;
  private String contactName;
  private String address;
  private String city;
  private String postalCode;
  private String country;
  private String phone;

  public CustomerCutDto(Customer customer) {
    this.id = customer.getId();
    this.companyName = customer.getCompanyName();
    this.contactName = customer.getContactName();
    this.address = customer.getAddress();
    this.city = customer.getCity();
    this.postalCode = customer.getPostalCode();
    this.country = customer.getCountry();
    this.phone = customer.getPhone();
  }
}
