package com.aceadoratech.jumpstart.service;

import com.aceadoratech.jumpstart.entity.Product;
import com.aceadoratech.jumpstart.entity.Status;
import com.aceadoratech.jumpstart.entity.Transaction;
import com.aceadoratech.jumpstart.exchanges.TransactionalRequest;
import com.aceadoratech.jumpstart.repository.ProductRepository;
import com.aceadoratech.jumpstart.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    public boolean createTransaction(TransactionalRequest transactionalRequest) {
        try {
            // Create a new transaction object using TransactionalRequest data
            var transaction = Transaction.builder()
                    .customerName(transactionalRequest.getCustomerName())
                    .address(transactionalRequest.getAddress())
                    .status(Status.PENDING)
                    .date(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()))
                    .products(transactionalRequest.getProduct())
                    .build();

            // Save the transaction object to the repository (assuming it's a valid repository object)
            transactionRepository.save(transaction);


            return true; // Return true indicating successful transaction creation
        } catch (Exception e) {
            throw e; // Rethrow the exception for error handling/logging purposes
        }
    }

    public List<Transaction> getAll(){return transactionRepository.findAll();}

}
