package com.tujuhsembilan.InventoryService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.InventoryService.model.Categories;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer>{
    Optional<Categories> findByCategoryNameIgnoreCase(String categoryName);
}
