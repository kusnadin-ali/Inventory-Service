package com.tujuhsembilan.InventoryService.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductCreateRequest {
    
    private Integer productId;

    private String productName;

    private Integer categoryId;

    private Double price;

    private String description;

    private Integer stock;

    private String sku;

    private Integer suppliersId;
}
