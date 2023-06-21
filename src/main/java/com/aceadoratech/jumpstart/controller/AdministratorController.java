package com.aceadoratech.jumpstart.controller;

import com.aceadoratech.jumpstart.entity.Product;
import com.aceadoratech.jumpstart.exchanges.ProductRequest;
import com.aceadoratech.jumpstart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdministratorController {
    private final ProductService productService;
    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest) {

        // Add the product using the productService
        productService.addProduct(productRequest);

        // Return a successful response with an OK status code and success message
        return ResponseEntity.ok().body("Successfully added new product");
    }

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        // Retrieve all products using the productService
        return productService.getAll();
    }

}
