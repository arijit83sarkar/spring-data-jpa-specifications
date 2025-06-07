package com.raven.spring_data_jpa_specifications.controller;

import com.raven.spring_data_jpa_specifications.model.Product;
import com.raven.spring_data_jpa_specifications.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(description = "API related to Product.", name = "Product")
@RequestMapping("/product")
@Slf4j
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Save a new Product.", description = "Save a new Product details.")
    @PostMapping(value = "/v1")
    public ResponseEntity<Product> saveProduct(@RequestBody Product productRequest) {
        log.info("ProductController - saveProduct() called...");
        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }
}
