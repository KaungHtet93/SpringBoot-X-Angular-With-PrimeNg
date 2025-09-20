package com.pearl.warehouse.repository;

import com.pearl.warehouse.model.Category;
import com.pearl.warehouse.model.Purchase;
import com.pearl.warehouse.model.PurchaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {
}

