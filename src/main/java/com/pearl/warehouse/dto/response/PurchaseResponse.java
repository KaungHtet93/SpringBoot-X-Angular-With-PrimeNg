package com.pearl.warehouse.dto.response;

import java.time.LocalDate;
import java.util.List;

public record PurchaseResponse(
  Integer id,
  LocalDate purchaseDate,
  SupplierNameResponse supplier,
  List<PurchaseDetailResponse> purchaseDetail
) {
}
