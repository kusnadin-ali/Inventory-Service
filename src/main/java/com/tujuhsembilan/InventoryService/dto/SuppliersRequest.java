package com.tujuhsembilan.InventoryService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SuppliersRequest {

    private String supplierName;

    private String contactInfo;
}
