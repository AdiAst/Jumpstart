package com.aceadoratech.jumpstart.service;

import com.aceadoratech.jumpstart.entity.Product;
import com.aceadoratech.jumpstart.exchanges.ProductRequest;
import com.aceadoratech.jumpstart.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Product addProduct(ProductRequest productRequest) {
        // Perform validation checks on productRequest data here (e.g., null checks, data integrity checks)
        try {
            var product = Product.builder()
                    .name(productRequest.getName())
                    .stock(productRequest.getStock())
                    .price(productRequest.getPrice())
                    .description(productRequest.getDescription())
                    .build();

            return repository.save(product); // Save the product and return the saved Product object
        } catch (Exception e) {
            // Handle the exception here (e.g., log the error, perform error-specific handling)
            throw e; // Rethrow the exception if needed
        }
    }

    public List<Product> getAll(){
        return repository.findAll();
    }
}
