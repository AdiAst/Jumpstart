package com.aceadoratech.jumpstart.controller;


import com.aceadoratech.jumpstart.entity.*;

import com.aceadoratech.jumpstart.config.JwtService;

import com.aceadoratech.jumpstart.exchanges.TransactionalRequest;
import com.aceadoratech.jumpstart.service.ProductService;
import com.aceadoratech.jumpstart.service.RetailRegionService;
import com.aceadoratech.jumpstart.service.TransactionService;
import com.aceadoratech.jumpstart.service.UserService;
import com.aceadoratech.jumpstart.utils.FilesHandler;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.apache.tomcat.util.json.JSONFilter;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/base")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequiredArgsConstructor
public class BaseController {

    // services ==================================================
    private final TransactionService transactionService;
    private final ProductService productService;
    private final JwtService jwtService;
    private final UserService userService;
    private final RetailRegionService retailRegionService;
    private final FilesHandler filesHandler;

    // transactions ==================================================
    @PostMapping("/transaction")
    public ResponseEntity<String> postTransaction(@RequestBody TransactionalRequest transactionalRequest) {

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

    @GetMapping("/transaction/{id}")
    public Transaction getTransaction(@PathVariable int id) {
        return transactionService.getTransaction(id);
    }

    // products ==================================================
    @GetMapping("/product")
    public List<Product> getAllProduct() {
        // Retrieve all products using the productService
        return productService.getAll();
    }

    @GetMapping("/product/{slug}")
    public Product getProduct(@PathVariable String slug) {
        return productService.getProduct(slug);
    }

    @GetMapping("/products")
    public List<Product> getProductByQuery(@RequestParam String query) {
        return productService.getProductByQuery(query);
    }

    // users ==================================================
    @GetMapping("/user/{token}")
    public UserLogin getSingleUser(@PathVariable String token){
        String email = jwtService.extractUsername(token);
        return userService.getSingleUser(email);
    }

    @PostMapping("/user/profile")
    public UserDetail postUpdateProfile(@RequestBody UserDetail userDetail) {
        return userService.updateUser(userDetail);
    }

    // image ==================================================
    @GetMapping("/product/image/{imageName}")
    public Resource getProductImage(@PathVariable String imageName) {
        return FilesHandler.getFile(imageName);
    }


}
