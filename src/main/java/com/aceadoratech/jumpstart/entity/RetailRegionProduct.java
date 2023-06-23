package com.aceadoratech.jumpstart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetailRegionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "retail_region_id")
    private RetailRegion retailRegion;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int stock;

}
