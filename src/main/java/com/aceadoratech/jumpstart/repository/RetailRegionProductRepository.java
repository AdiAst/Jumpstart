package com.aceadoratech.jumpstart.repository;

import com.aceadoratech.jumpstart.entity.RetailRegionProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetailRegionProductRepository extends JpaRepository<RetailRegionProduct,Integer> {
    List<RetailRegionProduct> findByRetailRegionId(Long retailRegionId);
}
