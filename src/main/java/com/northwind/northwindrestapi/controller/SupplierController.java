package com.northwind.northwindrestapi.controller;

import com.northwind.northwindrestapi.dto.SupplierDTO;
import com.northwind.northwindrestapi.entity.Supplier;
import com.northwind.northwindrestapi.service.interfaces.ISupplierService;
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
@RequestMapping(value = "/api/v2/supplier")
@AllArgsConstructor
public class SupplierController {

  private ISupplierService supplierService;

  @GetMapping
  public List<Supplier> getAllSuppliers() {
    return supplierService.getAllSuppliers();
  }

  @GetMapping(value = "/{id}")
  public Supplier getSupplier(@PathVariable int id) {
    return supplierService.getSupplier(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Supplier saveSupplier(@RequestBody Supplier supplier) {
    return supplierService.create(supplier);
  }

  @PutMapping(value = "/{id}")
  public Supplier updateSupplier(@PathVariable int id, @RequestBody Supplier supplier) {
    return supplierService.update(id, supplier);
  }

  @PatchMapping(value = "/{id}")
  public Supplier patch(@PathVariable int id, @RequestBody SupplierDTO supplier) {
    return supplierService.patch(id, supplier);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteSupplier(@PathVariable int id) {
    supplierService.delete(id);
  }

}
