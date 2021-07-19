package com.northwind.northwindrestapi.service.interfaces;

import com.northwind.northwindrestapi.dto.ProductPatchDTO;
import com.northwind.northwindrestapi.entity.Product;

import java.util.List;

public interface IProductService {

  List<Product> getAllProducts();

  Product getProduct(int id);

  Product create(Product product, int categoryId);

  Product update(int id, Product product);

  void delete(int id);

  Product patch(int id, ProductPatchDTO productPatchDTO);
}
