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

    public RetailRegionProduct addProductToRetailRegion(RetailRegionProduct retailRegionProduct) {
        return retailRegionProductRepository.save(retailRegionProduct);
    }
    public RetailRegion findById(Integer id){
        return retailRegionRepository.findById(id).get();
    }

    public RetailRegion addRetailRegion(RetailRegion retailRegion) {
        return retailRegionRepository.save(retailRegion);
    }

    public List<RetailRegion> getRetails() {
        return retailRegionRepository.findAll();
    }


}
