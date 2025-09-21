package com.pearl.warehouse.dto.response;

public record PurchaseDetailResponse(
//  Integer purchaseDetailId,
  ProductNameResponse product,
  Integer quantity,
  Double unitPrice,
  String uom
) {
}
