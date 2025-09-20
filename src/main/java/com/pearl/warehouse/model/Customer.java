package com.pearl.warehouse.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="customer")
@Getter
@Setter
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer customerId;
  private String customerName;
  private String email;
  private String address;
//  @OneToMany(mappedBy = "supplier")
//  private List<Purchase> purchaseList;
}
