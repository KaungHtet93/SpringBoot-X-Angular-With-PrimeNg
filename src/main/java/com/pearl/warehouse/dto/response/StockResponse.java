package com.pearl.warehouse.dto.response;

public record StockResponse(
  Integer id,
  ProductResponse product,
  Integer quantity,
  String uom,
  Double price
) {

}
