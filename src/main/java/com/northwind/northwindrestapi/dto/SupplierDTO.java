package com.northwind.northwindrestapi.dto;

import com.northwind.northwindrestapi.entity.Supplier;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SupplierDTO implements PatchDtoToEntityMapper<Supplier> {

  @ApiModelProperty(required = true)
  private String companyName;
  private String contactName;
  private String contactTitle;
  private String address;
  private String city;
  private String region;
  private String postalCode;
  private String country;
  private String phone;
  private String fax;
  private String homePage;

  @Override
  public void mapFromPatch(Supplier entity) {
    if (this.getCompanyName() != null) {
      entity.setCompanyName(this.getCompanyName());
    }
    if (this.getContactName() != null) {
      entity.setContactName(this.getContactName());
    }
    if (this.getContactTitle() != null) {
      entity.setContactTitle(this.getContactTitle());
    }
    if (this.getAddress() != null) {
      entity.setAddress(this.getAddress());
    }
    if (this.getCity() != null) {
      entity.setCity(this.getCity());
    }
    if (this.getRegion() != null) {
      entity.setRegion(this.getPostalCode());
    }
    if (this.getCountry() != null) {
      entity.setCountry(this.getCountry());
    }
    if (this.getPhone() != null) {
      entity.setPhone(this.getPhone());
    }
    if (this.getFax() != null) {
      entity.setFax(this.getFax());
    }
  }

}
