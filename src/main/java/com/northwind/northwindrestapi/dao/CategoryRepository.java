package com.northwind.northwindrestapi.dao;

import com.northwind.northwindrestapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
