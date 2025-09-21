package com.pearl.warehouse.dto.response;

public record SupplierNameResponse(
  Integer supplierId,
  String name,
  String email,
  String phone
) {
}
