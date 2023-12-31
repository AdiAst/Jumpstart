package com.aceadoratech.jumpstart.service;

import com.aceadoratech.jumpstart.entity.*;
import com.aceadoratech.jumpstart.exchanges.TransactionalRequest;
import com.aceadoratech.jumpstart.repository.ProductRepository;
import com.aceadoratech.jumpstart.repository.RetailRegionProductRepository;
import com.aceadoratech.jumpstart.repository.RetailRegionRepository;
import com.aceadoratech.jumpstart.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final RetailRegionRepository retailRegionRepository;
    private final RetailRegionProductRepository retailRegionProductRepository;

    public boolean createTransaction(TransactionalRequest transactionalRequest) {
        try {
            // Create a new transaction object using TransactionalRequest data
            var transaction = Transaction.builder()
                    .customerName(transactionalRequest.getCustomerName())
                    .address(transactionalRequest.getAddress())
                    .status(Status.PENDING)
                    .date(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()))
                    .retailRegionProduct(retailRegionProductRepository.findById(transactionalRequest.getRetailRegionProductId()).get())
                    .build();

            // Save the transaction object to the repository (assuming it's a valid repository object)
            transactionRepository.save(transaction);

            return true; // Return true indicating successful transaction creation
        } catch (Exception e) {
            throw e; // Rethrow the exception for error handling/logging purposes
        }
    }
    public boolean approveTransaction(Integer id) {
        Transaction transaction = transactionRepository.findById(id).get();
        RetailRegionProduct retailRegionProduct = transaction.getRetailRegionProduct();
        if (retailRegionProduct != null && transaction != null) {
            retailRegionProduct.setStock(retailRegionProduct.getStock() - 1);
            transaction.setStatus(Status.DONE);
            retailRegionProductRepository.save(retailRegionProduct);
            transactionRepository.save(transaction);
            return true;
        } else {
            // Handle case when the retailRegionProduct is not found
            return false;
        }
    }
    public List<Transaction> getByStatus(Status status){
        return transactionRepository.findByStatus(status);
    }
    public Transaction getTransaction(int id) {
        return transactionRepository.findById(id).get();
    }
    public List<Transaction> getAll(){return transactionRepository.findAll();}

}
