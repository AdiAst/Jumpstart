package com.aceadoratech.jumpstart.repository;

import com.aceadoratech.jumpstart.entity.Product;
import com.aceadoratech.jumpstart.entity.RetailRegion;
import com.aceadoratech.jumpstart.entity.RetailRegionProduct;
import com.aceadoratech.jumpstart.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RetailRegionProductRepository extends JpaRepository<RetailRegionProduct,Integer> {

    List<RetailRegionProduct> findByRetailRegionId(Long retailRegionId);
    List<RetailRegionProduct> findByProductId(Long productId);
}
