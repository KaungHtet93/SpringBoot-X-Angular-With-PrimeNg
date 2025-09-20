package com.pearl.warehouse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="supplier")
public class Supplier {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer supplierId;
  private String name;
  private String email;
  private String phone;
  @OneToMany(mappedBy = "supplier")
  private List<Purchase> purchaseList;
}
