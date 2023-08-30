package com.tujuhsembilan.InventoryService.service;

import java.util.List;

import com.tujuhsembilan.InventoryService.dto.CategoryRequest;
import com.tujuhsembilan.InventoryService.dto.CategoryResponse;
import com.tujuhsembilan.InventoryService.dto.ProductCreateRequest;
import com.tujuhsembilan.InventoryService.dto.ProductSupplierResponse;
import com.tujuhsembilan.InventoryService.dto.ProductsResponse;
import com.tujuhsembilan.InventoryService.dto.ProductsUpdateRequest;
import com.tujuhsembilan.InventoryService.dto.StockProductResponse;
import com.tujuhsembilan.InventoryService.dto.StockRequest;
import com.tujuhsembilan.InventoryService.dto.SuppliersRequest;
import com.tujuhsembilan.InventoryService.dto.SuppliersResponse;

public interface IinventoryService {
    // Category
    void createCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getAllCategory();

    CategoryResponse getDetailCategory(Integer id);

    void updateCategory(Integer id, CategoryRequest categoryRequest);

    void deleteCategory(Integer id);

    // Products
    void createProduct(ProductCreateRequest request); //with relations with Category and create new Product-Supplier Relation

    List<ProductsResponse> getAllProduct();

    ProductsResponse getDetailProduct(Integer id);

    void updateProduct(Integer id, ProductsUpdateRequest request);

    void deleteProduct(Integer id);

    // Stock
    void updateStockProduct(Integer productId,StockRequest stockRequest);

    StockProductResponse getStockProduct(Integer productId);

    List<ProductsResponse> getProductInEmptyStock();

    // Suppliers
    void createSuppliers(SuppliersRequest request);

    List<SuppliersResponse> getAllSuppliers();

    SuppliersResponse getDetailSuppliers(Integer id);

    void updateSuppliers(Integer id, SuppliersRequest request);

    void deleteSuppliers(Integer id);

    // Product-Supplier Relation
    ProductSupplierResponse getProductFromSuppliers(Integer suppliersId);
}
