package com.northwind.northwindrestapi.dto;

import com.northwind.northwindrestapi.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryPatchDTO implements PatchDtoToEntityMapper<Category> {

  private String name;
  private String description;

  @Override
  public void mapFromPatch(Category entity) {
    if (this.getName() != null) {
      entity.setName(this.getName());
    }
    if (this.getDescription() != null) {
      entity.setDescription(this.getDescription());
    }
  }
}
