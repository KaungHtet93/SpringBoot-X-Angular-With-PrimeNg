package com.pearl.warehouse.dto.response;

public record ProductNameResponse(
  Integer productId,
  String productName,
  String code,
  Double basePrice
) {
}
