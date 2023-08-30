package com.tujuhsembilan.InventoryService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsResponse {

    private Integer productId;

    private String productName;

    private Integer categoryId;

    private Double price;

    private String description;

    private String sku;
}
