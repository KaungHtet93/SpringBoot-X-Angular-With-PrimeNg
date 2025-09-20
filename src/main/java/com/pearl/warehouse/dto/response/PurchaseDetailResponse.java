package com.pearl.warehouse.dto.response;

public record PurchaseDetailResponse(
  Integer purchaseDetailId,
  ProductResponse product,
  Integer quantity,
  Double unitPrice,
  String uom
) {
}
