package com.pearl.warehouse.mapper;

import com.pearl.warehouse.dto.response.ProductResponse;
import com.pearl.warehouse.dto.response.PurchaseResponse;
import com.pearl.warehouse.model.Purchase;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface PurchaseMapper {
  PurchaseResponse toPurchaseResponse(Purchase purchase);
  List<PurchaseResponse> toPurcListResponse(List<Purchase> purchaseList);
}
