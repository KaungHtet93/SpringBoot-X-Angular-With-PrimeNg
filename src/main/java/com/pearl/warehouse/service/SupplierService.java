package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.SupplierInput;
import com.pearl.warehouse.model.Supplier;
import com.pearl.warehouse.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
  @Autowired
  private SupplierRepository supplierRepository;

  public List<Supplier> getAllSuppliers(){
    return supplierRepository.findAll();
  }
  public Supplier saveSupplier(SupplierInput dto){
    Supplier supplier = new Supplier();
//    supplier.setSupplierId(dto.id());
    supplier.setName(dto.name());
    supplier.setPhone(dto.phone());
    supplier.setEmail(dto.email());
    Supplier savedSupplier = supplierRepository.save(supplier);
    return savedSupplier;
  }
  public Optional<Supplier> getSupplierById(Integer id){
    return supplierRepository.findById(id);
  }
  public Supplier updateSupplier(Integer id,SupplierInput dto){
    Optional<Supplier> supplierOptional = supplierRepository.findById(id);
    if(supplierOptional.isPresent()){
      Supplier supplier = supplierOptional.get();
      supplier.setEmail(dto.email());
      supplier.setName(dto.name());
      supplier.setPhone(dto.phone());
      return supplierRepository.save(supplier);
    }else throw new RuntimeException("Supplier not found");
  }

  public Boolean deleteById(Integer id) {
     supplierRepository.deleteById(id);
     return true;
  }
}
