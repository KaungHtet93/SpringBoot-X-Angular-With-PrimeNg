package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.StockInput;
import com.pearl.warehouse.mapper.StockMapper;
import com.pearl.warehouse.model.Product;
import com.pearl.warehouse.model.Stock;
import com.pearl.warehouse.repository.ProductRepository;
import com.pearl.warehouse.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

  @Autowired
  private StockRepository stockRepository;
  @Autowired
  private ProductRepository productRepository;
  private final StockMapper stockMapper;

  public StockService(StockMapper stockMapper) {
    this.stockMapper = stockMapper;
  }

  public Stock saveStock(StockInput stockInput) {
    Stock stock = stockMapper.toEntity(stockInput);
    Stock stockCreated = stockRepository.save(stock);
    return stockCreated;
  }

  public List<Stock> getAllStocks() {
    return stockRepository.findAll();
  }

  public Optional<Stock> findById(Integer id) {
    return stockRepository.findById(id);
  }

  public Stock update(Integer id, StockInput stockInput) {
    Optional<Stock> stock = stockRepository.findById(id);
    if (stock.isPresent()) {
      Optional<Product> product = productRepository.findById(stockInput.productId());
      Stock stock1 = stock.get();
      stock1.setUom(stockInput.uom());
      stock1.setPrice(stockInput.price());
      stock1.setQuantity(stockInput.quantity());
      stock1.setProduct(product.get());
      return stockRepository.save(stock1);
    } else throw new RuntimeException("Stock item not found");
  }
}
