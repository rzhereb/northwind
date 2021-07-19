package com.northwind.northwindrestapi.controller;

import com.northwind.northwindrestapi.dto.ProductPatchDTO;
import com.northwind.northwindrestapi.entity.Product;
import com.northwind.northwindrestapi.service.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v2/product")
@AllArgsConstructor
public class ProductController {

  private IProductService productService;

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping(value = "/{id}")
  public Product getProduct(@PathVariable int id) {
    return productService.getProduct(id);
  }

  @PostMapping(value = "/category/{categoryId}")
  @ResponseStatus(HttpStatus.CREATED)
  public Product saveProduct(@RequestBody Product product, @PathVariable int categoryId) {
    return productService.create(product, categoryId);
  }

  @PutMapping(value = "/{id}")
  public Product updateProduct(@PathVariable int id, @RequestBody Product product) {
    return productService.update(id, product);
  }

  @PatchMapping(value = "/{id}")
  public Product patch(@PathVariable int id, @RequestBody ProductPatchDTO productPatchDTO) {
    return productService.patch(id, productPatchDTO);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProduct(@PathVariable int id) {
    productService.delete(id);
  }

}
