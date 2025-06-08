package com.raven.spring_data_jpa_specifications.service;

import com.raven.spring_data_jpa_specifications.model.dto.ProductRequestDto;
import com.raven.spring_data_jpa_specifications.model.dto.ProductResponseDto;
import com.raven.spring_data_jpa_specifications.model.dto.ReviewResponseDto;
import com.raven.spring_data_jpa_specifications.model.dto.SearchRequestDto;
import com.raven.spring_data_jpa_specifications.model.entity.Product;
import com.raven.spring_data_jpa_specifications.model.entity.Review;
import com.raven.spring_data_jpa_specifications.repository.IProductRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final IProductRepo productRepo;
    private final ProductCriteriaQueryService queryService;

    @Autowired
    public ProductService(IProductRepo productRepo, ProductCriteriaQueryService queryService) {
        this.productRepo = productRepo;
        this.queryService = queryService;
    }

    @Transactional
    public ProductResponseDto saveProduct(ProductRequestDto productRequest) {
        log.info("ProductService - saveProduct() called...");

        // Product
        Product product = ProductHelperService.getProduct(productRequest);

        // Reviews
        List<Review> reviewList = ProductHelperService.getReviews(productRequest, product);
        product.setReviews(reviewList);

        log.info("ProductService - saveProduct() ends...");
        return ProductHelperService.getResponse(productRepo.saveAndFlush(product));
    }

    public List<ProductResponseDto> searchByCriteriaQuery(SearchRequestDto requestDto) {
        List<Product> products = queryService.nameLike(requestDto.pName());

        return ProductHelperService.getResponse(products);
    }
}
