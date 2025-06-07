package com.raven.spring_data_jpa_specifications.service;

import com.raven.spring_data_jpa_specifications.model.Product;
import com.raven.spring_data_jpa_specifications.repository.IProductRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final IProductRepo productRepo;

    @Autowired
    public ProductService(IProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product saveProduct(Product productRequest) {
        log.info("saveProduct() called...");
        return productRepo.saveAndFlush(productRequest);
    }
}
