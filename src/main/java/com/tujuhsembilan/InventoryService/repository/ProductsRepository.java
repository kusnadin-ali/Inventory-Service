package com.tujuhsembilan.InventoryService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.InventoryService.model.Products;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    List<Products> findByStock_QuantityLessThan(Integer quantity);
}
