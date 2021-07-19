package com.northwind.northwindrestapi.service;

import com.northwind.northwindrestapi.dao.ProductRepository;
import com.northwind.northwindrestapi.dto.ProductPatchDTO;
import com.northwind.northwindrestapi.entity.Category;
import com.northwind.northwindrestapi.entity.Product;
import com.northwind.northwindrestapi.service.interfaces.ICategoryService;
import com.northwind.northwindrestapi.service.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

  private ProductRepository productRepository;
  private ICategoryService categoryService;

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product getProduct(int id) {
    return productRepository.findById(id).orElse(null);
  }

  @Override
  public Product create(Product product, int categoryId) {
    final Category category = categoryService.getCategory(categoryId);
    product.setCategory(category);
    return productRepository.saveAndFlush(product);
  }

  @Override
  public Product update(int id, Product product) {
    if (productRepository.existsById(id)) {
      product.setId(id);
      return productRepository.saveAndFlush(product);
    }
    return null;
  }

  @Override
  public void delete(int id) {
    productRepository.deleteById(id);
  }

  @Override
  public Product patch(int id, ProductPatchDTO productPatchDTO) {
    final Product product = productRepository.findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No Product with id %s found", id)));
    productPatchDTO.mapFromPatch(product);
    final Category category = categoryService.getCategory(productPatchDTO.getCategory());
    if (category != null) {
      product.setCategory(category);
    }
    productRepository.saveAndFlush(product);
    return product;
  }
}
