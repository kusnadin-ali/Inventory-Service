package com.tujuhsembilan.InventoryService.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductSupplierResponse {
    private Integer supplierId;

    private String supplierName;

    private String contactInfo;

    private List<ProductsResponse> products;
}
