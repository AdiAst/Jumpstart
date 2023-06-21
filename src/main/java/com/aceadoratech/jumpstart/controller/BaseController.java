package com.aceadoratech.jumpstart.controller;

import com.aceadoratech.jumpstart.exchanges.TransactionalRequest;
import com.aceadoratech.jumpstart.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/base")
@RequiredArgsConstructor
public class BaseController {
    private final TransactionService transactionService;

    @PostMapping("add-transaction")
    public ResponseEntity<String> addTransactional(TransactionalRequest transactionalRequest){
        transactionService.createTransaction(transactionalRequest);
        return ResponseEntity.ok().body("Successfully Ordering the Product");
    }
}
