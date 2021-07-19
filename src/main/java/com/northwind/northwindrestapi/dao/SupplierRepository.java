package com.northwind.northwindrestapi.dao;

import com.northwind.northwindrestapi.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
