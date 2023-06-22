package com.aceadoratech.jumpstart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int stock;

    @Column(length = 3000)
    private String description;
    private float price;

    @Column(unique = true)
    private String slug;
    public String picture;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Transaction> transaction;
}
