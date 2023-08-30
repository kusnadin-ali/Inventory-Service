package com.tujuhsembilan.InventoryService.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
import com.tujuhsembilan.InventoryService.model.Categories;
import com.tujuhsembilan.InventoryService.model.Products;
import com.tujuhsembilan.InventoryService.model.Stock;
import com.tujuhsembilan.InventoryService.model.Suppliers;
import com.tujuhsembilan.InventoryService.repository.CategoriesRepository;
import com.tujuhsembilan.InventoryService.repository.ProductsRepository;
import com.tujuhsembilan.InventoryService.repository.StockRepository;
import com.tujuhsembilan.InventoryService.repository.SuppliersRepository;

@Service
public class InventoryService implements IinventoryService {

    @Lazy
    @Autowired
    private CategoriesRepository categoriesRepository;

    @Lazy
    @Autowired
    private ProductsRepository productsRepository;

    @Lazy
    @Autowired
    private StockRepository stockRepository;

    @Lazy
    @Autowired
    private SuppliersRepository suppliersRepository;

    @Override
    public void createCategory(CategoryRequest categoryRequest) {
        Optional<Categories> categories = categoriesRepository.findByCategoryName(categoryRequest.getCategoryName());
        if (categories.isPresent()) {
            throw new RuntimeException("Categories is already exist");
        }
        Categories newCategories = new Categories();
        newCategories.setCategoryName(categoryRequest.getCategoryName());
        newCategories.setDescription(categoryRequest.getDescription());
        categoriesRepository.save(newCategories);
    }

    @Override
    public void createProduct(ProductCreateRequest request) {
        Products newProducts = new Products();
        newProducts.setProductName(request.getProductName());
        newProducts.setPrice(request.getPrice());
        newProducts.setDescription(request.getDescription());
        newProducts.setCreatedAt(Timestamp.valueOf(LocalDateTime.now().toString()));
        newProducts.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now().toString()));
        newProducts.setSku(request.getSku());

        Optional<Categories> categories = categoriesRepository.findById(request.getCategoryId());
        if (categories.isPresent()) {
            newProducts.setCategories(categories.get());
        }

        Optional<Suppliers> suppliers = suppliersRepository.findById(request.getSuppliersId());
        if (suppliers.isPresent()) {
            newProducts.setSuppliers(suppliers.get());
        }

        newProducts = productsRepository.save(newProducts);

        Stock newStock = new Stock();
        newStock.setLastUpdated(Timestamp.valueOf(LocalDateTime.now().toString()));
        newStock.setProducts(newProducts);
        newStock.setQuantity(request.getStock());
        stockRepository.save(newStock);
    }

    @Override
    public void createSuppliers(SuppliersRequest request) {
        Suppliers newSuppliers = new Suppliers();
        newSuppliers.setContactInfo(request.getContactInfo());
        newSuppliers.setSupplierName(request.getSupplierName());
        suppliersRepository.save(newSuppliers);
    }

    @Override
    public void deleteCategory(Integer id) {
        Optional<Categories> category = categoriesRepository.findById(id);

        category.ifPresent(c -> {
            categoriesRepository.delete(c);
        });
    }

    @Override
    public void deleteProduct(Integer id) {
        Optional<Products> products = productsRepository.findById(id);
        products.ifPresent(p -> {
            productsRepository.delete(p);
        });
    }

    @Override
    public void deleteSuppliers(Integer id) {
        Optional<Suppliers> suppliers = suppliersRepository.findById(id);
        suppliers.ifPresent(s -> {
            suppliersRepository.delete(s);
        });

    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<Categories> categories = categoriesRepository.findAll();
        List<CategoryResponse> responses = new ArrayList<>();

        categories.forEach(c -> {
            CategoryResponse temp = new CategoryResponse();
            temp.setCategoryId(c.getCategoryId());
            temp.setCategoryName(c.getCategoryName());
            temp.setDescription(c.getDescription());
            responses.add(temp);
        });
        return responses;
    }

    @Override
    public List<ProductsResponse> getAllProduct() {
        List<Products> products = productsRepository.findAll();
        List<ProductsResponse> responses = new ArrayList<>();
        products.forEach(p -> {
            ProductsResponse response = new ProductsResponse();
            response.setCategoryId(p.getCategories().getCategoryId());
            response.setDescription(p.getDescription());
            response.setPrice(p.getPrice());
            response.setProductId(p.getProductId());
            response.setProductName(p.getProductName());
            response.setSku(p.getSku());
            responses.add(response);
        });
        return responses;
    }

    @Override
    public List<SuppliersResponse> getAllSuppliers() {
        List<Suppliers> suppliers = suppliersRepository.findAll();
        List<SuppliersResponse> responses = new ArrayList<>();
        suppliers.forEach(s -> {
            SuppliersResponse response = new SuppliersResponse();
            response.setContactInfo(s.getContactInfo());
            response.setSupplierId(s.getSupplierId());
            response.setSupplierName(s.getSupplierName());
            responses.add(response);
        });
        return responses;
    }

    @Override
    public CategoryResponse getDetailCategory(Integer id) {
        Optional<Categories> category = categoriesRepository.findById(id);
        CategoryResponse response = new CategoryResponse();
        category.ifPresent(c -> {
            response.setCategoryId(c.getCategoryId());
            response.setCategoryName(c.getCategoryName());
            response.setDescription(c.getDescription());
        });
        return response;

    }

    @Override
    public ProductsResponse getDetailProduct(Integer id) {
        Optional<Products> products = productsRepository.findById(id);
        ProductsResponse response = new ProductsResponse();
        products.ifPresent(p -> {
            response.setCategoryId(p.getCategories().getCategoryId());
            response.setDescription(p.getDescription());
            response.setPrice(p.getPrice());
            response.setProductId(p.getProductId());
            response.setProductName(p.getProductName());
            response.setSku(p.getSku());
        });
        return response;
    }

    @Override
    public SuppliersResponse getDetailSuppliers(Integer id) {
        Optional<Suppliers> suppliers = suppliersRepository.findById(id);
        SuppliersResponse response = new SuppliersResponse();
        suppliers.ifPresent(s -> {
            response.setContactInfo(s.getContactInfo());
            response.setSupplierId(s.getSupplierId());
            response.setSupplierName(s.getSupplierName());
        });
        return response;
    }

    @Override
    public ProductSupplierResponse getProductFromSuppliers(Integer suppliersId) {
        Optional<Suppliers> suppliers = suppliersRepository.findById(suppliersId);
        ProductSupplierResponse response = new ProductSupplierResponse();
        suppliers.ifPresent(s -> {
            response.setContactInfo(s.getContactInfo());
            response.setSupplierId(s.getSupplierId());
            response.setSupplierName(s.getSupplierName());

            List<ProductsResponse> pResponses = new ArrayList<>();
            s.getProducts().forEach(p -> {
                ProductsResponse pResponse = new ProductsResponse();
                pResponse.setCategoryId(p.getCategories().getCategoryId());
                pResponse.setDescription(p.getDescription());
                pResponse.setPrice(p.getPrice());
                pResponse.setProductId(p.getProductId());
                pResponse.setProductName(p.getProductName());
                pResponse.setSku(p.getSku());

                pResponses.add(pResponse);
            });
            response.setProducts(pResponses);
        });
        return response;
    }

    @Override
    public List<ProductsResponse> getProductInEmptyStock() {
        List<Products> products = productsRepository.findByStock_QuantityLessThan(1);
        List<ProductsResponse> responses = new ArrayList<>();
        products.forEach(p -> {
            ProductsResponse response = new ProductsResponse();
            response.setCategoryId(p.getCategories().getCategoryId());
            response.setDescription(p.getDescription());
            response.setPrice(p.getPrice());
            response.setProductId(p.getProductId());
            response.setProductName(p.getProductName());
            response.setSku(p.getSku());
            responses.add(response);
        });
        return responses;
    }

    @Override
    public StockProductResponse getStockProduct(Integer productId) {
        Optional<Products> products = productsRepository.findById(productId);
        StockProductResponse response = new StockProductResponse();
        products.ifPresent(p -> {
            response.setDescription(p.getDescription());
            response.setPrice(p.getPrice());
            response.setProductId(productId);
            response.setProductName(p.getProductName());
            response.setQuantity(p.getStock().getQuantity());
            response.setSku(p.getSku());
            response.setSuppliersId(p.getSuppliers().getSupplierId());
        });
        return response;
    }

    @Override
    public void updateCategory(Integer id, CategoryRequest categoryRequest) {
        Optional<Categories> category = categoriesRepository.findByCategoryName(categoryRequest.getCategoryName());
        if (category.isPresent() && (category.get().getCategoryId() == id)) {
            category.ifPresent(c -> {
                c.setCategoryName(categoryRequest.getCategoryName());
                c.setDescription(categoryRequest.getDescription());
                categoriesRepository.save(c);
            });
        } else {
            throw new RuntimeException("Categories is already exist");
        }
    }

    @Override
    public void updateProduct(Integer id, ProductsUpdateRequest request) {
        Optional<Products> products = productsRepository.findById(id);

        products.ifPresent(p -> {
            p.setDescription(request.getDescription());
            p.setPrice(request.getPrice());
            p.setProductName(request.getProductName());
            p.setSku(request.getSku());
            p.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now().toString()));
            productsRepository.save(p);
        });
    }

    @Override
    public void updateStockProduct(Integer productId, StockRequest stockRequest) {
        Optional<Products> products = productsRepository.findById(productId);
        products.ifPresent(p -> {
            Stock stock = p.getStock();
            stock.setLastUpdated(Timestamp.valueOf(LocalDateTime.now().toString()));
            stock.setQuantity(stockRequest.getQuantity());
            stockRepository.save(stock);
        });

    }

    @Override
    public void updateSuppliers(Integer id, SuppliersRequest request) {
        Optional<Suppliers> suppliers = suppliersRepository.findById(id);
        suppliers.ifPresent(s -> {
            s.setContactInfo(request.getContactInfo());
            s.setSupplierName(request.getSupplierName());
            suppliersRepository.save(s);
        });

    }

}
