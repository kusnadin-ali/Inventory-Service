package com.tujuhsembilan.InventoryService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductsUpdateRequest {

    private String productName;

    private Integer categoryId;

    private Double price;

    private String description;

    private String sku;
}
