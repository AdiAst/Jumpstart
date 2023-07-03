package com.aceadoratech.jumpstart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @JoinColumn(name = "retail_region_id", nullable = true)
    private RetailRegion retailRegion;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;

    private int stock;

    @JsonIgnore
    @OneToMany(mappedBy = "retailRegionProduct", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
