package com.northwind.northwindrestapi.service;

import com.northwind.northwindrestapi.dao.CategoryRepository;
import com.northwind.northwindrestapi.dto.CategoryPatchDTO;
import com.northwind.northwindrestapi.entity.Category;
import com.northwind.northwindrestapi.service.interfaces.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

  private CategoryRepository categoryRepository;

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public Category getCategory(int id) {
    return categoryRepository.findById(id).orElse(null);
  }

  @Override
  public Category create(Category category, MultipartFile file) {
    if (file != null && !file.isEmpty()) {
      try {
        category.setPicture(file.getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return categoryRepository.saveAndFlush(category);
  }

  @Override
  public Category update(int id, Category category) {
    if (categoryRepository.existsById(id)) {
      category.setId(id);
      return categoryRepository.saveAndFlush(category);
    }
    return null;
  }

  @Override
  public void delete(int id) {
    categoryRepository.deleteById(id);
  }

  @Override
  public byte[] getCategoryPicture(int id) {
    return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("No category with id: " + id)).getPicture();
  }

  @Override
  public Category patch(int id, CategoryPatchDTO categoryPatchDTO) {
    final Category category = categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, String.format("No Category with id %s found", id)));
    categoryPatchDTO.mapFromPatch(category);
    return categoryRepository.saveAndFlush(category);
  }
}
