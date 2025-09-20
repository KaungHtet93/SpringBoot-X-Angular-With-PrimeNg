package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.input.CategoryInput;
import com.pearl.warehouse.dto.input.CategoryWithProducts;
import com.pearl.warehouse.dto.input.SupplierInput;
import com.pearl.warehouse.model.Category;
import com.pearl.warehouse.model.Supplier;
import com.pearl.warehouse.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

  @Autowired
  private SupplierService supplierService;

  @PostMapping("/save")
  public Supplier saveSupplier(@RequestBody SupplierInput categoryInput) {
    return supplierService.saveSupplier(categoryInput);
  }

  @GetMapping("/list")
  public List<Supplier> getAllSuppliers() {
    return supplierService.getAllSuppliers();
  }

  @GetMapping("{id}")
  public Optional<Supplier> findById(@PathVariable(value = "id") Integer id) {
    return supplierService.getSupplierById(id);
  }

  @PutMapping("/update/{id}")
  public Supplier updateCategory(@PathVariable Integer id, @RequestBody SupplierInput categoryInput) {
    return supplierService.updateSupplier(id, categoryInput);
  }

  @DeleteMapping("/delete/{id}")
  public Boolean deleteCategory(@PathVariable Integer id) {
    return supplierService.deleteById(id);
  }
}
