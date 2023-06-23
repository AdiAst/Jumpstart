package com.aceadoratech.jumpstart.controller;

import com.aceadoratech.jumpstart.entity.Product;
import com.aceadoratech.jumpstart.entity.RetailRegion;
import com.aceadoratech.jumpstart.entity.RetailRegionProduct;
import com.aceadoratech.jumpstart.entity.Transaction;
import com.aceadoratech.jumpstart.exchanges.ProductRequest;
import com.aceadoratech.jumpstart.exchanges.RetailProductRequest;
import com.aceadoratech.jumpstart.service.ProductService;
import com.aceadoratech.jumpstart.service.RetailRegionService;
import com.aceadoratech.jumpstart.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdministratorController {

    // services ==================================================
    private final ProductService productService;
    private final RetailRegionService retailRegionService;
    private final TransactionService transactionService;

    // products ==================================================
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
    // Retail  ==================================================
    @PostMapping("/retail-regions")
    public RetailRegion addRetailRegion(@RequestBody RetailRegion retailRegion) {
        return retailRegionService.addRetailRegion(retailRegion);
    }
    @PostMapping("/retail-regions/product")
    public RetailRegionProduct addProductToRetailRegion(
            @RequestBody RetailProductRequest retailProductRequest
            ) {
        RetailRegion retailRegion = retailRegionService.findById(retailProductRequest.getRetailId());
        Product product = productService.getProduct(retailProductRequest.getProductId());
        RetailRegionProduct retailRegionProduct = new RetailRegionProduct();
        retailRegionProduct.setRetailRegion(retailRegion);
        retailRegionProduct.setProduct(product);
        return retailRegionService.addProductToRetailRegion(retailRegionProduct);
    }


    // transactions ==================================================

    @GetMapping("/transaction")
    public List<Transaction> getAllTransaction(){
        // Retrieve all transaction using the transactionService
        return transactionService.getAll();
    }


    @GetMapping("/retail-regions/{retailRegionId}/products")
    public List<RetailRegionProduct> getProductsByRetailRegionId(@PathVariable Long retailRegionId) {
        return retailRegionService.getProductsByRetailRegionId(retailRegionId);
    }

}
