package com.tujuhsembilan.InventoryService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tujuhsembilan.InventoryService.dto.CategoryRequest;
import com.tujuhsembilan.InventoryService.dto.ProductCreateRequest;
import com.tujuhsembilan.InventoryService.dto.ProductsUpdateRequest;
import com.tujuhsembilan.InventoryService.dto.StockRequest;
import com.tujuhsembilan.InventoryService.dto.SuppliersRequest;
import com.tujuhsembilan.InventoryService.service.IinventoryService;
import com.tujuhsembilan.util.ResponseHandler;

@Controller
public class InventoryController {
    
    @Autowired
    private IinventoryService inventoryService;

    @PostMapping("/categories/create")
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest){
        try {
            inventoryService.createCategory(categoryRequest);
            return ResponseHandler.generateResponse("Create Category Success", HttpStatus.CREATED);
        } catch (RuntimeException  e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle other validation or unexpected exceptions here
            return ResponseHandler.generateResponse("An error occurred during category creation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<Object> getAllCategory(){
        return ResponseHandler.generateResponse("Find All Category Success", HttpStatus.OK, inventoryService.getAllCategory());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Object> getDetailCategory(@PathVariable("id") Integer id){
        return ResponseHandler.generateResponse("Find Category By Id Success", HttpStatus.OK, inventoryService.getDetailCategory(id));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable("id") Integer id, @RequestBody CategoryRequest categoryRequest){
        try {
            inventoryService.updateCategory(id,categoryRequest);
            return ResponseHandler.generateResponse("Update Category Success", HttpStatus.NO_CONTENT);
        } catch (RuntimeException  e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle other validation or unexpected exceptions here
            return ResponseHandler.generateResponse("An error occurred during category creation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Integer id){
        inventoryService.deleteCategory(id);
        return ResponseHandler.generateResponse("Delete Category By Id Success", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/products/create")
    public ResponseEntity<Object> createProduct(@RequestBody ProductCreateRequest request){
        inventoryService.createProduct(request);
        return ResponseHandler.generateResponse("Create Products Success", HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseHandler.generateResponse("Find All Products Success", HttpStatus.OK, inventoryService.getAllProduct());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getDetailProducts(@PathVariable("id") Integer id){
        return ResponseHandler.generateResponse("Find Products By Id Success", HttpStatus.OK, inventoryService.getDetailProduct(id));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProducts(@PathVariable("id") Integer id, @RequestBody ProductsUpdateRequest request){
        inventoryService.updateProduct(id, request);
        return ResponseHandler.generateResponse("Update products By Id Success", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProducts(@PathVariable("id") Integer id){
        inventoryService.deleteProduct(id);
        return ResponseHandler.generateResponse("Delete Products By Id Success", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/products/stock/{productId}")
    public ResponseEntity<Object> updateStock(@PathVariable("productId") Integer productId, @RequestBody StockRequest request){
        inventoryService.updateStockProduct(productId, request);
        return ResponseHandler.generateResponse("Update Stock products By Id Products Success", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/products/stock/{productId}")
    public ResponseEntity<Object> getStockProduct(@PathVariable("productId") Integer productId){
        return ResponseHandler.generateResponse("Find Stock Products Success", HttpStatus.OK, inventoryService.getStockProduct(productId));
    }

    @GetMapping("/products/stock/empty")
    public ResponseEntity<Object> getEmptyStockProducts(){
        return ResponseHandler.generateResponse("Find Empty Stock Products Success", HttpStatus.OK, inventoryService.getProductInEmptyStock());
    }

    @PostMapping("/suppliers/create")
    public ResponseEntity<Object> createProduct(@RequestBody SuppliersRequest request){
        inventoryService.createSuppliers(request);
        return ResponseHandler.generateResponse("Create Suppliers Success", HttpStatus.CREATED);
    }

    @GetMapping("/suppliers")
    public ResponseEntity<Object> getSuppliers(){
        return ResponseHandler.generateResponse("Find All Suppliers Success", HttpStatus.OK, inventoryService.getAllSuppliers());
    }

    @GetMapping("/suppliers/{id}")
    public ResponseEntity<Object> getDetailSuppliers(@PathVariable("id") Integer id){
        return ResponseHandler.generateResponse("Find Suppliers By Id Success", HttpStatus.OK, inventoryService.getDetailSuppliers(id));
    }

    @PutMapping("/suppliers/{id}")
    public ResponseEntity<Object> updateSuppliers(@PathVariable("id") Integer id, @RequestBody SuppliersRequest request){
        inventoryService.updateSuppliers(id, request);
        return ResponseHandler.generateResponse("Update Supplier By Id Success", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<Object> deleteSuppliers(@PathVariable("id") Integer id){
        inventoryService.deleteSuppliers(id);
        return ResponseHandler.generateResponse("Delete Supplier By Id Success", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/suppliers/product/{suppliersId}")
    public ResponseEntity<Object> getProductSuppliers(@PathVariable("suppliersId") Integer id){
        return ResponseHandler.generateResponse("Find Suppliers Products By SuppliersId Success", HttpStatus.OK, inventoryService.getProductFromSuppliers(id));
    }
}
