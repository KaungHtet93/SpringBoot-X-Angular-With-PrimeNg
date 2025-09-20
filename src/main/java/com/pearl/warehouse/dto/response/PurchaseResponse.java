package com.pearl.warehouse.dto.response;

import java.time.LocalDate;

public record PurchaseResponse(
  Integer id,
  SupplierResponse supplier,
  LocalDate purchaseDate,
  PurchaseDetailResponse purchaseDetail
) {
}
