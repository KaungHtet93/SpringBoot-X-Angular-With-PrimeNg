package com.pearl.warehouse.dto.response;

import java.time.LocalDate;

public record PurchaseResponse(
  Integer id,
  LocalDate purchaseDate,
  SupplierNameResponse supplier,
  PurchaseDetailResponse purchaseDetail
) {
}
