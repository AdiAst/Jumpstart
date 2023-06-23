package com.aceadoratech.jumpstart.service;

import com.aceadoratech.jumpstart.entity.Product;
import com.aceadoratech.jumpstart.exchanges.ProductRequest;
import com.aceadoratech.jumpstart.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Product addProduct(ProductRequest productRequest) {
        // Perform validation checks on productRequest data here (e.g., null checks, data integrity checks)
        try {
            var product = Product.builder()
                    .name(productRequest.getName())
                    .price(productRequest.getPrice())
                    .picture(productRequest.getPicture())
                    .slug(productRequest.getName().replace(" ", "_").toLowerCase())
                    .description(productRequest.getDescription())
                    .build();

            return repository.save(product); // Save the product and return the saved Product object
        } catch (Exception exception) {
            // Handle the exception here (e.g., log the error, perform error-specific handling)
            log.error(exception.getMessage());
            return null;
        }
    }

    public List<Product> getAll(){
        return repository.findAll();
    }

    public Product getProduct(String slug) {
        return repository.findBySlug(slug);
    }
    public Product getProduct(Integer id){return repository.findById(id).get();}
    public List<Product> getProductByQuery(String query) {
        return repository.findByQuery(query);
    }
}
