package com.pearl.warehouse.dto.input;

import java.time.LocalDate;
import java.util.List;

public record PurchaseInput(
  Integer supplierId,
  LocalDate orderDate,
  List<PurchaseDetailInput> details
){
}
