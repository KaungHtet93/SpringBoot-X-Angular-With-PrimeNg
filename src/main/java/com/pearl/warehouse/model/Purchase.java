package com.pearl.warehouse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.ap.internal.model.GeneratedType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="purchase")
public class Purchase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer purchaseId;
  @ManyToOne
  @JoinColumn(name = "supplier_id", nullable = false)
  private Supplier supplier;
  private LocalDate purchaseDate;
  @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PurchaseDetails> details = new ArrayList<>();
  public void addDetail(PurchaseDetails detail) {
    details.add(detail);
    detail.setPurchase(this);
  }

  public void removeDetail(PurchaseDetails detail) {
    details.remove(detail);
    detail.setPurchase(null);
  }
}
