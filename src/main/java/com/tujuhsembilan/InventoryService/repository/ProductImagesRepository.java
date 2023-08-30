package com.tujuhsembilan.InventoryService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.InventoryService.model.ProductImages;

@Repository
public interface ProductImagesRepository extends JpaRepository<ProductImages, Integer>{
    
}
