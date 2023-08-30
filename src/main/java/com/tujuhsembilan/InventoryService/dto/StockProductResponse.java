package com.tujuhsembilan.InventoryService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockProductResponse {
    private Integer productId;
    
    private Integer quantity;
    
    private String productName;

    private Double price;

    private String description;

    private String sku;

    private Integer suppliersId;


}
