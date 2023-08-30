package com.tujuhsembilan.InventoryService.model;

import java.sql.Timestamp;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Integer stockId;

    // @Column(name = "product_id")
    // private Integer productId;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Products products;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "last_updated")
    private Timestamp lastUpdated;
}
