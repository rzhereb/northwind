package com.northwind.northwindrestapi.dao;

import com.northwind.northwindrestapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
