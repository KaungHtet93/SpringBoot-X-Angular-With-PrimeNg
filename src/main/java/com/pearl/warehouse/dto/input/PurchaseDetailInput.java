package com.pearl.warehouse.dto.input;

public record PurchaseDetailInput(
  Integer productId,
  Integer quantity,
  Double unitPrice,
  String uom
) {
}
