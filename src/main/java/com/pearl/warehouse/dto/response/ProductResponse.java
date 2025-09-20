package com.pearl.warehouse.dto.response;

public record ProductResponse(
  Integer productId,
  String productName,
  String code,
  Double basePrice,
  CategoryResponse category
) {
}
