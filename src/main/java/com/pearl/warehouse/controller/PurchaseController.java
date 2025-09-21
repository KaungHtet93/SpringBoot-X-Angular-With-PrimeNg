package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.input.PurchaseInput;
import com.pearl.warehouse.dto.response.PurchaseResponse;
import com.pearl.warehouse.model.Purchase;
import com.pearl.warehouse.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
  @Autowired
  private PurchaseService purchaseService;
  @PostMapping("/save")
  public PurchaseResponse savePurchase(@RequestBody PurchaseInput dto){
    return  purchaseService.savePurchase(dto);
  }
  @GetMapping("/list")
  public List<PurchaseResponse> getAllPurchases(){
    return purchaseService.getAllPurchases();
  }
  @GetMapping("{id}")
  public PurchaseResponse getPurchaseById(@PathVariable Integer id){
    return purchaseService.getPurchaseWithDetails(id);
  }
  @PutMapping("/update/{id}")
  public PurchaseResponse updatePurchase(@PathVariable Integer id,@RequestBody PurchaseInput dto){
    return purchaseService.updatePurchase(id,dto);
  }
  @DeleteMapping("{id}")
  public Boolean deletePurchase(@PathVariable Integer id){
    return purchaseService.deletePurchase(id);
  }
}
