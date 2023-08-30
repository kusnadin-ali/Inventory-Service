package com.tujuhsembilan.InventoryService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.InventoryService.model.Suppliers;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Integer>{
    
}
