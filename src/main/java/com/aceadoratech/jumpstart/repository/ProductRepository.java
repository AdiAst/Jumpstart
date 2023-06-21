package com.aceadoratech.jumpstart.repository;

import com.aceadoratech.jumpstart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
