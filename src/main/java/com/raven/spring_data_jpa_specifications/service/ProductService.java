package com.raven.spring_data_jpa_specifications.service;

import com.raven.spring_data_jpa_specifications.model.Product;
import com.raven.spring_data_jpa_specifications.model.Review;
import com.raven.spring_data_jpa_specifications.repository.IProductRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.collection.spi.PersistentBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final IProductRepo productRepo;

    @Autowired
    public ProductService(IProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Transactional
    public Product saveProduct(Product productRequest) {
        log.info("ProductService - saveProduct() called...");

        // Product
        Product product = new Product(productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.isAvailable(),
                productRequest.getManufacturer());

        // Reviews
        Set<Review> reviewList = new HashSet<>();
        productRequest.getReviews().forEach(r -> {
            Review review = new Review(r.getRating(), r.getComment());
            review.setProduct(product);
            reviewList.add(review);
        });
        product.setReviews(reviewList);

        log.info("ProductService - saveProduct() ends...");
        return productRepo.saveAndFlush(product);
    }
}
