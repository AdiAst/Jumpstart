package com.aceadoratech.jumpstart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerName;
    private String address;
    private Status status;
    private String total;
    private String date;

    @ManyToOne
    @JoinColumn(name="retail_id")
    private RetailRegionProduct retailRegionProduct;
}
