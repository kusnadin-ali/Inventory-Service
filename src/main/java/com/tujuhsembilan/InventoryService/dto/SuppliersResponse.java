package com.tujuhsembilan.InventoryService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SuppliersResponse {
    private Integer supplierId;

    private String supplierName;

    private String contactInfo;
}
