package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.PurchaseInput;
import com.pearl.warehouse.dto.response.PurchaseResponse;
import com.pearl.warehouse.mapper.PurchaseMapper;
import com.pearl.warehouse.model.*;
import com.pearl.warehouse.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

  @Autowired
  private PurchaseRepository purchaseRepository;
  @Autowired
  private StockRepository stockRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private SupplierRepository supplierRepository;
  private PurchaseMapper purchaseMapper;
  public PurchaseService(PurchaseMapper purchaseMapper){
    this.purchaseMapper=purchaseMapper;
  }
  public List<PurchaseResponse> getAllPurchases() {
    List<Purchase> purchaseList= purchaseRepository.findAll();
    return purchaseMapper.toPurcListResponse(purchaseList);
  }

  public PurchaseResponse getPurchaseWithDetails(Integer id) {
    Purchase purchase= purchaseRepository.findById(id).orElseThrow(()->new RuntimeException("Purchase Not found"));
    return purchaseMapper.toPurchaseResponse(purchase);
  }

  @Transactional
  public PurchaseResponse savePurchase(PurchaseInput dto) {
    Purchase purchase = new Purchase();
    Supplier supplier = supplierRepository.findById(dto.supplierId())
      .orElseThrow(() -> new RuntimeException("Supplier not found with id " + dto.supplierId()));
    purchase.setSupplier(supplier);
    purchase.setPurchaseDate(dto.orderDate());

    dto.details().forEach(d -> {
      PurchaseDetails detail = new PurchaseDetails();
      detail.setPurchase(purchase);
      detail.setProduct(productRepository.findById(d.productId())
        .orElseThrow(() -> new RuntimeException("Product not found")));
      detail.setQuantity(d.quantity());
      detail.setUnitPrice(d.unitPrice());
      detail.setUom(d.uom());

      purchase.addDetail(detail);

      // update stock
      updateStockQuantity(d.productId(), d.quantity(), d.unitPrice(), d.uom());
    });

    Purchase savePurchase= purchaseRepository.save(purchase);
    return purchaseMapper.toPurchaseResponse(savePurchase);
  }

  @Transactional
  public PurchaseResponse updatePurchase(Integer id, PurchaseInput dto) {
    Purchase purchase = purchaseRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Purchase not found with id " + id));

    // rollback old stock first
    purchase.getDetails().forEach(d -> rollbackStockQuantity(d.getProduct().getProductId(), d.getQuantity()));

    purchase.getDetails().clear(); // remove old details
    Supplier supplier = supplierRepository.findById(dto.supplierId())
      .orElseThrow(() -> new RuntimeException("Supplier not found with id " + dto.supplierId()));
    purchase.setSupplier(supplier);
    purchase.setPurchaseDate(dto.orderDate());

    dto.details().forEach(d -> {
      PurchaseDetails pd = new PurchaseDetails();
      pd.setProduct(productRepository.findById(d.productId())
        .orElseThrow(() -> new RuntimeException("Product not found")));
      pd.setQuantity(d.quantity());
      pd.setUnitPrice(d.unitPrice());
      pd.setUom(d.uom());
      pd.setPurchase(purchase);
      purchase.addDetail(pd);

      // apply new stock
      updateStockQuantity(d.productId(), d.quantity(), d.unitPrice(), d.uom());
    });

    Purchase savePurchase= purchaseRepository.save(purchase);
    return purchaseMapper.toPurchaseResponse(savePurchase);
  }

  @Transactional
  public Boolean deletePurchase(Integer id) {
    Purchase purchase = purchaseRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Purchase not found"));

    // rollback stock
    purchase.getDetails().forEach(d -> rollbackStockQuantity(d.getProduct().getProductId(), d.getQuantity()));

    purchaseRepository.delete(purchase);
    return true;
  }

  // --- stock helpers ---
  private void updateStockQuantity(Integer productId, Integer qty, Double price, String uom) {
    Stock stock = stockRepository.findByProduct_ProductId(productId).orElseGet(() -> {
      Stock s = new Stock();
      Product product = productRepository.findById(productId)
        .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
      s.setProduct(product);
      s.setQuantity(0);
      return s;
    });

    stock.setQuantity(stock.getQuantity() + qty);
    stock.setPrice(price);
    stock.setUom(uom);
    stockRepository.save(stock);
  }

  private void rollbackStockQuantity(Integer productId, int qty) {
    Stock stock = stockRepository.findByProduct_ProductId(productId)
      .orElseThrow(() -> new RuntimeException("Stock not found for product id " + productId));

    if (stock.getQuantity() < qty) {
      throw new RuntimeException("Not enough stock to rollback for product id " + productId);
    }

    stock.setQuantity(stock.getQuantity() - qty);
    stockRepository.save(stock);
  }
}
