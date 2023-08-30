package com.tujuhsembilan.InventoryService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryRequest {
    private String categoryName;

    private String description;
}
