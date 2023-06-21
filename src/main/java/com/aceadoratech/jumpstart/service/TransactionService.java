package com.aceadoratech.jumpstart.service;

import com.aceadoratech.jumpstart.entity.Status;
import com.aceadoratech.jumpstart.entity.Transaction;
import com.aceadoratech.jumpstart.exchanges.TransactionalRequest;
import com.aceadoratech.jumpstart.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    public boolean createTransaction(TransactionalRequest transactionalRequest) {
        try {
            // Create a new transaction object using TransactionalRequest data
            var transaction = Transaction.builder()
                    .customerName(transactionalRequest.getCustomerName())
                    .address(transactionalRequest.getAddress())
                    .status(Status.PENDING)
                    .date(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime()))
                    .product(transactionalRequest.getProduct())
                    .build();

            // Save the transaction object to the repository (assuming it's a valid repository object)
            repository.save(transaction);

            return true; // Return true indicating successful transaction creation
        } catch (Exception e) {
            throw e; // Rethrow the exception for error handling/logging purposes
        }
    }

}
