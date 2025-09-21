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
  @Column(name = "purchase_id") // DB column name
  private Integer id;
  @ManyToOne
  @JoinColumn(name = "supplier_id", nullable = false)
  private Supplier supplier;
  private LocalDate purchaseDate;
  @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
  private List<PurchaseDetails> purchaseDetail = new ArrayList<>();
  public void addDetail(PurchaseDetails detail) {
    purchaseDetail.add(detail);
    detail.setPurchase(this);
  }

  public void removeDetail(PurchaseDetails detail) {
    purchaseDetail.remove(detail);
    detail.setPurchase(null);
  }
}
