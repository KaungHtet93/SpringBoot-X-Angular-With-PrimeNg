package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.input.PurchaseDto;
import com.pearl.warehouse.model.Purchase;
import com.pearl.warehouse.model.PurchaseDetails;
import com.pearl.warehouse.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
  @Autowired
  private PurchaseService purchaseService;
  @PostMapping("/save")
  public Purchase savePurchase(@RequestBody PurchaseDto dto){
    return  purchaseService.savePurchase(dto);
  }
  @GetMapping("/list")
  public List<Purchase> getAllPurchases(){
    return purchaseService.getAllPurchases();
  }
  @GetMapping("{id}")
  public Optional<Purchase> getPurchaseById(@PathVariable Integer id){
    return purchaseService.getPurchaseWithDetails(id);
  }
  @PutMapping("/update/{id}")
  public Purchase updatePurchase(@PathVariable Integer id,@RequestBody PurchaseDto dto){
    return purchaseService.updatePurchase(id,dto);
  }
  @DeleteMapping("{id}")
  public Boolean deletePurchase(@PathVariable Integer id){
    return purchaseService.deletePurchase(id);
  }
}
