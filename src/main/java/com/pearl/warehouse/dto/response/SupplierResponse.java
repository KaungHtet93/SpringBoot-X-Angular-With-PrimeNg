package com.pearl.warehouse.dto.response;

import com.pearl.warehouse.model.Purchase;

public record SupplierResponse(
  Integer supplierId,
  String name,
  String email,
  String phone,
  PurchaseResponse purchase
) {
}
