package com.tujuhsembilan.InventoryService.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "product_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Integer imageId;

    // @Column(name = "product_id")
    // private Integer productId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_primary")
    private Boolean isPrimary;
}
