package com.tujuhsembilan.InventoryService.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.NumberFormat;

import lombok.*;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    // @Column(name = "category_id")
    // private Integer categoryId;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Categories categories;

    @Column(name = "price")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    private String sku;

    @OneToOne(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    private Stock stock;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImages> productImages;

    @ManyToOne
    @JoinColumn(name = "suppliers_id")
    private Suppliers suppliers;
}
