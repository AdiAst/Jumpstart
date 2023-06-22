package com.aceadoratech.jumpstart.controller;

import com.aceadoratech.jumpstart.entity.Product;
import com.aceadoratech.jumpstart.exchanges.ProductRequest;
import com.aceadoratech.jumpstart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        Product product = productService.addProduct(productRequest);
        if(product == null)
            return new ResponseEntity<>(
                    String.format("'%s' is exists", productRequest.getName()), HttpStatus.NOT_ACCEPTABLE
            );

        // Return a successful response with an OK status code and success message
        return ResponseEntity.ok().body("Successfully added new product");
    }

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        // Retrieve all products using the productService
        return productService.getAll();
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @GetMapping("/product")
    public List<Product> getProductByQuery(@RequestParam String query) {
        return productService.getProductByQuery(query);
    }

}
