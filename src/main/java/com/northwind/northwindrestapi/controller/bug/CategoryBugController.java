package com.northwind.northwindrestapi.controller.bug;

import com.northwind.northwindrestapi.controller.bug.dto.CategoryId;
import com.northwind.northwindrestapi.dto.CategoryPatchDTO;
import com.northwind.northwindrestapi.entity.Category;
import com.northwind.northwindrestapi.service.interfaces.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/category")
@AllArgsConstructor
public class CategoryBugController {

  private ICategoryService categoryService;

  @GetMapping
  public List<CategoryId> getAllCategories() {
    return categoryService.getAllCategories()
        .stream().map(category -> new CategoryId(category.getId())).collect(Collectors.toList());
  }

  @GetMapping(value = "/{id}")
  public Category getCategory(@PathVariable int id) {
    return categoryService.getCategory(id);
  }

  @GetMapping(value = "/picture/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
  public ResponseEntity<byte[]> getCategoryPicture(@PathVariable int id) {
    return ResponseEntity.ok().body(categoryService.getCategoryPicture(id));
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Category saveCategory(@ModelAttribute Category category, @RequestParam(required = false) MultipartFile file) {
    return categoryService.create(category, file);
  }

  @PutMapping(value = "/{id}")
  public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
    return categoryService.update(id, category);
  }

  @PatchMapping(value = "/{id}")
  public Category updateCategory(@PathVariable int id, @RequestBody CategoryPatchDTO category) {
    return categoryService.patch(id, category);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCategory(@PathVariable int id) {
  }
}
