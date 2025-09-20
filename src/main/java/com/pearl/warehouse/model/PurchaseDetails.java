package com.pearl.warehouse.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="purchaseDetails")
public class PurchaseDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer purchaseDetailId;
  @ManyToOne
  @JoinColumn(name="purchase_id")
  private Purchase purchase;
  @ManyToOne
  @JoinColumn(name="product_id")
  private Product product;
  private Integer quantity;
  private Double unitPrice;
  private String uom;
}
