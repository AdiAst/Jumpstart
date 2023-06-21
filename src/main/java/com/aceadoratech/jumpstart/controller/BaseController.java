package com.aceadoratech.jumpstart.controller;

import com.aceadoratech.jumpstart.exchanges.TransactionalRequest;
import com.aceadoratech.jumpstart.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/base")
@RequiredArgsConstructor
public class BaseController {
    private final TransactionService transactionService;

    @PostMapping("/add-transaction")
    public ResponseEntity<String> addTransactional(@RequestBody TransactionalRequest transactionalRequest) {
        // Call the transactionService to create the transaction
        boolean created = transactionService.createTransaction(transactionalRequest);

        // Check if the transaction was created successfully
        if (created) {
            // Return a successful response with an OK status code and success message
            return ResponseEntity.ok().body("Successfully ordered the product");
        } else {
            // Return an error response with an Internal Server Error status code and error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create transaction");
        }
    }

}
