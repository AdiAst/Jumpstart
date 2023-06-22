package com.aceadoratech.jumpstart.repository;

import com.aceadoratech.jumpstart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product WHERE " +
            "name LIKE %:query% AND " +
            "description LIKE %:query%", nativeQuery = true)
    List<Product> findByQuery(String query);
}
