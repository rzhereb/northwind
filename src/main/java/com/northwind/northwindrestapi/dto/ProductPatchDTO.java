package com.northwind.northwindrestapi.dto;

import com.northwind.northwindrestapi.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductPatchDTO implements PatchDtoToEntityMapper<Product> {

  private String name;
  private String quantityPerUnit;
  private double unitPrice;
  private int unitsInStock;
  private int unitsOnOrder;
  private int reorderLevel;
  private int category;

  @Override
  public void mapFromPatch(Product entity) {
    if (this.getName() != null) {
      entity.setName(this.getName());
    }
    if (this.getQuantityPerUnit() != null) {
      entity.setQuantityPerUnit(this.getQuantityPerUnit());
    }
    if (this.getUnitPrice() != 0.0) {
      entity.setUnitPrice(this.getUnitPrice());
    }
    if (this.getUnitsInStock() != 0) {
      entity.setUnitsInStock(this.getUnitsInStock());
    }
    if (this.getUnitsOnOrder() != 0) {
      entity.setUnitsOnOrder(this.getUnitsOnOrder());
    }
    if (this.getReorderLevel() != 0) {
      entity.setReorderLevel(this.getReorderLevel());
    }
    if (this.getName() != null) {
      entity.setName(this.getName());
    }

  }
}
