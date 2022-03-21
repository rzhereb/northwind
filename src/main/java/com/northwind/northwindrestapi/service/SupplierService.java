package com.northwind.northwindrestapi.service;

import com.northwind.northwindrestapi.dao.SupplierRepository;
import com.northwind.northwindrestapi.dto.SupplierDTO;
import com.northwind.northwindrestapi.entity.Supplier;
import com.northwind.northwindrestapi.service.interfaces.ISupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService implements ISupplierService {

  private SupplierRepository supplierRepository;

  @Override
  public List<Supplier> getAllSuppliers() {
    return supplierRepository.findAll();
  }

  @Override
  public Supplier getSupplier(int id) {
    return supplierRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, String.format("No Supplier with id %s found", id)));
  }

  @Override
  public Supplier create(Supplier supplier) {
    return supplierRepository.saveAndFlush(supplier);
  }

  @Override
  public Supplier update(int id, Supplier supplier) {
    if (!supplierRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No Supplier with id %s found", id));
    }
    supplier.setId(id);
    return supplierRepository.saveAndFlush(supplier);
  }

  @Override
  public void delete(int id) {
    final Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, String.format("No Supplier with id %s found", id)));
    supplierRepository.delete(supplier);
  }

  @Override
  public Supplier patch(int id, SupplierDTO supplierPatchDTO) {
    final Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
        HttpStatus.NOT_FOUND, String.format("No Supplier with id %s found", id)));
    supplierPatchDTO.mapFromPatch(supplier);
    return supplierRepository.saveAndFlush(supplier);
  }
}
