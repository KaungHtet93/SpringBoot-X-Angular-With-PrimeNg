package com.pearl.warehouse.mapper;

import com.pearl.warehouse.dto.input.ProductInput;
import com.pearl.warehouse.dto.response.CategoryResponse;
import com.pearl.warehouse.dto.response.ProductResponse;
import com.pearl.warehouse.model.Category;
import com.pearl.warehouse.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
//  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
//  @Mapping(source = "categoryId" , target = "category")
//  Product toEntity(ProductInput dto);
  ProductResponse toProductResponse(Product product);
  CategoryResponse toCategoryResponse(Category category);
  List<ProductResponse> toProductListResponse(List<Product> products);
}
