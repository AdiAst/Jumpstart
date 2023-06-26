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
import com.aceadoratech.jumpstart.utils.FilesHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdministratorController {

    // services ==================================================
    private final ProductService productService;
    private final RetailRegionService retailRegionService;
    private final TransactionService transactionService;
    private final FilesHandler filesHandler;

    // products ==================================================
    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest) {
        // add the product using the productService
        Product product = productService.addProduct(productRequest);
        if(product == null)
            return new ResponseEntity<>(String.format("'%s' is exists", productRequest.getName()), HttpStatus.NOT_ACCEPTABLE);

        // return a successful response with an OK status code and success message
        return ResponseEntity.ok().body("Successfully added new product");
    }

    @PostMapping("/product/image")
    public ResponseEntity<?> postProductImage(@RequestParam("image") MultipartFile image) throws IOException {
        // check if image size lower than 2MB
        if(image.getSize() > 2000000) return ResponseEntity.ok("Image size cannot more than 2MB");
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        String dir = "storage/images/"; // store to local directory
        filesHandler.saveFile(dir, imageName, image); // write the image

        return ResponseEntity.ok().body("Image added to storage");
    }


    // retails  ==================================================
    @PostMapping("/retail-regions")
    public ResponseEntity<String> addRetailRegion(@RequestBody RetailRegion retailRegion) {
        retailRegionService.addRetailRegion(retailRegion);
        return ResponseEntity.ok().body("Successfully Create a Retail Region");
    }

    @PostMapping("/retail-regions/product")
    public ResponseEntity<String> addProductToRetailRegion(@RequestBody RetailProductRequest retailProductRequest) {
        RetailRegion retailRegion = retailRegionService.findById(retailProductRequest.getRetailId());
        Product product = productService.getProduct(retailProductRequest.getProductId());
        RetailRegionProduct retailRegionProduct = new RetailRegionProduct();
        retailRegionProduct.setRetailRegion(retailRegion);
        retailRegionProduct.setProduct(product);
        retailRegionProduct.setStock(retailProductRequest.getStock());
        retailRegionService.addProductToRetailRegion(retailRegionProduct);
        return ResponseEntity.ok().body("Successfully add product to retail region");
    }

    @GetMapping("/retail-regions/{retailRegionId}/products")
    public List<RetailRegionProduct> getProductsByRetailRegionId(@PathVariable Long retailRegionId) {
        return retailRegionService.getProductsByRetailRegionId(retailRegionId);
    }

    // transactions ==================================================
    @GetMapping("/transaction")
    public List<Transaction> getAllTransaction(){
        // Retrieve all transaction using the transactionService
        return transactionService.getAll();
    }
    @PostMapping("/transaction/approve/{id}")
    public ResponseEntity<String> approveTransaction(@PathVariable Integer id){
        transactionService.approveTransaction(id);
        return ResponseEntity.ok().body("Successfully approve a product");
    }

}
