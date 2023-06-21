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
    @PostMapping("product")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest){
        System.out.println(productRequest);
        productService.addProduct(productRequest);
        return ResponseEntity.ok().body("Successfully Added New Product");
    }
    @GetMapping("product")
    public List<Product> getAllProduct(){
        return productService.getAll();
    }
}
