package com.aceadoratech.jumpstart.controller;

import com.aceadoratech.jumpstart.entity.*;
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
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AdministratorController {

    // services ==================================================
    private final ProductService productService;
    private final RetailRegionService retailRegionService;
    private final TransactionService transactionService;
    private final FilesHandler filesHandler;

    // products ==================================================
    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody Product productRequest) {
        // add the product using the productService
        Product product = productService.addProduct(productRequest);
        if(product == null)
            return new ResponseEntity<>(String.format("'%s' is exists", productRequest.getName()), HttpStatus.NOT_ACCEPTABLE);

        // return a successful response with an OK status code and success message
        return ResponseEntity.ok().body("Successfully added new product");
    }

    @PutMapping("/product")
    public ResponseEntity<String> updateProduct(@RequestBody Product productRequest) {
        // add the product using the productService
        Product product = productService.addProduct(productRequest);
        if(product == null)
            return new ResponseEntity<>(String.format("'%s' is exists", productRequest.getName()), HttpStatus.NOT_ACCEPTABLE);

        // return a successful response with an OK status code and success message
        return ResponseEntity.ok().body("Successfully update product");
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        List<RetailRegionProduct> retailRegionProducts = retailRegionService.getProductsByProductId(id.longValue());

        for (RetailRegionProduct retailRegionProduct : retailRegionProducts) {
            retailRegionService.deleteProduct(retailRegionProduct.getId());
        }

        productService.deleteProduct(id);

        return ResponseEntity.ok().body("Successfully delete product");
    }

    @PostMapping("/product/image")
    public ResponseEntity<?> postProductImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam("customName") String customName
    ) throws IOException {
        // check if image size lower than 2MB
        if(image.getSize() > 2000000) return ResponseEntity.ok("Image size cannot more than 2MB");
        String imageName = StringUtils.cleanPath(customName);
        String dir = "storage/images/"; // store to local directory
        filesHandler.saveFile(dir, imageName, image); // write the image

        return ResponseEntity.ok().body("Image added to storage");
    }

    // retail regions  ==================================================
    @GetMapping("/retail-regions")
    public List<RetailRegion> getRetails() {
        return retailRegionService.getRetails();
    }

    @GetMapping("/retail-regions/{id}")
    public RetailRegion getRetail(@PathVariable Integer id) {
        return retailRegionService.getRetail(id);
    }

    @PostMapping("/retail-regions")
    public ResponseEntity<String> addRetailRegion(@RequestBody RetailRegion retailRegion) {
        retailRegionService.addRetailRegion(retailRegion);
        return ResponseEntity.ok().body("Successfully Create a Retail Region");
    }

    // update retail regions
    @PutMapping("/retail-regions")
    public ResponseEntity<?> updateRetailRegions(@RequestBody RetailRegion retailRegion) {
        retailRegionService.updateRetail(retailRegion);
        return ResponseEntity.ok("Retail regions updated");
    }

    @DeleteMapping("/retail-regions/{id}")
    public ResponseEntity<?> deleteRetailRegions(@PathVariable Integer id) {
        retailRegionService.deleteRetail(id);
        return ResponseEntity.ok("Retail regions deleted");
    }

    // retail regions products ==================================================
    @PostMapping("/retail-regions/product")
    public ResponseEntity<String> addProductToRetailRegion(@RequestBody RetailProductRequest retailProductRequest) {
        RetailRegion retailRegion = retailRegionService.getRetail(retailProductRequest.getRetailId());
        Product product = productService.getProduct(retailProductRequest.getProductId());
        RetailRegionProduct retailRegionProduct = new RetailRegionProduct();
        retailRegionProduct.setRetailRegion(retailRegion);
        retailRegionProduct.setProduct(product);
        retailRegionProduct.setStock(retailProductRequest.getStock());
        retailRegionService.addProductToRetailRegion(retailRegionProduct);
        return ResponseEntity.ok().body("Successfully add product to retail region");
    }

    @GetMapping("/retail-regions/{retailRegionId}/retails")
    public List<RetailRegionProduct> getProductsByRetailRegionId(@PathVariable Long retailRegionId) {
        return retailRegionService.getProductsByRetailRegionId(retailRegionId);
    }
    @GetMapping("/retail-regions/{productId}/products")
    public List<RetailRegionProduct> getProductsByProductId(@PathVariable Long productId) {
        return retailRegionService.getProductsByProductId(productId);
    }
    // update retail products
    @PutMapping("/retail-regions/product")
    public ResponseEntity<?> updateRetailProducts(@RequestBody RetailRegionProduct retailRegionProduct) {
        retailRegionService.updateProduct(retailRegionProduct);
        return ResponseEntity.ok("Product updated");
    }

    @DeleteMapping("/retail-regions/product/{id}")
    public ResponseEntity<?> deleteRetailProducts(@PathVariable Integer id) {
        retailRegionService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted");
    }

    // transactions ==================================================
    @GetMapping("/transaction")
    public List<Transaction> getAllTransaction(){
        // Retrieve all transaction using the transactionService
        return transactionService.getAll();
    }
    @GetMapping("/transaction/{status}")
    public List<Transaction> getTransactionByStatus(@PathVariable Status status){
        return transactionService.getByStatus(status);
    }
    @GetMapping("/transaction/approve/{id}")
    public ResponseEntity<String> approveTransaction(@PathVariable Integer id){
        transactionService.approveTransaction(id);
        return ResponseEntity.ok().body("Successfully approve a product");
    }
}