package com.northwind.northwindrestapi.dao;

import com.northwind.northwindrestapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
