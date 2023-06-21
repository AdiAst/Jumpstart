package com.aceadoratech.jumpstart.repository;

import com.aceadoratech.jumpstart.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
