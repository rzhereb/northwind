package com.northwind.northwindrestapi.service.interfaces;

import com.northwind.northwindrestapi.dto.CategoryPatchDTO;
import com.northwind.northwindrestapi.entity.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICategoryService {

  List<Category> getAllCategories();

  Category getCategory(int id);

  Category create(Category category, MultipartFile file);

  Category update(int id, Category category);

  void delete(int id);

  byte[] getCategoryPicture(int id);

  Category patch(int id, CategoryPatchDTO category);
}
