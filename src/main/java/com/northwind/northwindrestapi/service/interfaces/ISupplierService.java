package com.northwind.northwindrestapi.service.interfaces;

import com.northwind.northwindrestapi.dto.SupplierDTO;
import com.northwind.northwindrestapi.entity.Supplier;

import java.util.List;

public interface ISupplierService {

  List<Supplier> getAllSuppliers();

  Supplier getSupplier(int id);

  Supplier create(Supplier Supplier);

  Supplier update(int id, Supplier Supplier);

  void delete(int id);

  Supplier patch(int id, SupplierDTO Supplier);
}
