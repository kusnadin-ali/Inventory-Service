package com.tujuhsembilan.InventoryService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryResponse {
    private Integer categoryId;

    private String categoryName;

    private String description;
}
