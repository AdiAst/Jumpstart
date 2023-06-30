package com.aceadoratech.jumpstart.service;

import com.aceadoratech.jumpstart.entity.RetailRegion;
import com.aceadoratech.jumpstart.entity.RetailRegionProduct;
import com.aceadoratech.jumpstart.repository.RetailRegionProductRepository;
import com.aceadoratech.jumpstart.repository.RetailRegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RetailRegionService {
    private final RetailRegionProductRepository retailRegionProductRepository;
    private final RetailRegionRepository retailRegionRepository;

    public List<RetailRegionProduct> getProductsByRetailRegionId(Long retailRegionId) {
        return retailRegionProductRepository.findByRetailRegionId(retailRegionId);
    }
    public List<RetailRegionProduct> getProductsByProductId(Long productId) {
        return retailRegionProductRepository.findByProductId(productId);
    }
    public RetailRegionProduct addProductToRetailRegion(RetailRegionProduct retailRegionProduct) {
        return retailRegionProductRepository.save(retailRegionProduct);
    }


    public RetailRegion addRetailRegion(RetailRegion retailRegion) {
        return retailRegionRepository.save(retailRegion);
    }

    public List<RetailRegion> getRetails() {
        return retailRegionRepository.findAll();
    }

    public RetailRegion getRetail(Integer id) {
        return retailRegionRepository.findById(id).get();
    }

    public void updateRetail(RetailRegion rr) {
        RetailRegion retailRegion = retailRegionRepository.findById(rr.getId()).get();
        retailRegion.setName(rr.getName());
        retailRegion.setLocation(rr.getLocation());
        retailRegionRepository.save(retailRegion);
    }

    public void deleteRetail(Integer id) {
        retailRegionRepository.deleteById(id);
    }

    public void updateProduct(RetailRegionProduct rrp) {
        RetailRegionProduct retailRegionProduct = retailRegionProductRepository.findById(rrp.getId()).get();
        retailRegionProduct.setProduct(rrp.getProduct());
        retailRegionProduct.setStock(rrp.getStock());
        retailRegionProduct.setRetailRegion(rrp.getRetailRegion());
        retailRegionProductRepository.save(retailRegionProduct);
    }

    public void deleteProduct(Integer id) {
        retailRegionProductRepository.deleteById(id);
    }
}
