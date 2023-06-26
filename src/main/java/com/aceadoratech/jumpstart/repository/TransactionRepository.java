package com.aceadoratech.jumpstart.repository;

import com.aceadoratech.jumpstart.entity.Product;
import com.aceadoratech.jumpstart.entity.RetailRegion;
import com.aceadoratech.jumpstart.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

}
