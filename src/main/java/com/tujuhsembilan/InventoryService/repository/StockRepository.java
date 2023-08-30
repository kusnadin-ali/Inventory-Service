package com.tujuhsembilan.InventoryService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.InventoryService.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer>{
    
}
