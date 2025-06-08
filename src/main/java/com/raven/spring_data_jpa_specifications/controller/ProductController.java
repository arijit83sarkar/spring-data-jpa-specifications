package com.raven.spring_data_jpa_specifications.controller;

import com.raven.spring_data_jpa_specifications.model.dto.ProductRequestDto;
import com.raven.spring_data_jpa_specifications.model.dto.ProductResponseDto;
import com.raven.spring_data_jpa_specifications.model.dto.SearchRequestDto;
import com.raven.spring_data_jpa_specifications.model.entity.Product;
import com.raven.spring_data_jpa_specifications.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody ProductRequestDto productRequest) {
        log.info("ProductController - saveProduct() called...");
        return ResponseEntity.ok(productService.saveProduct(productRequest));
    }

    @Operation(summary = "Search product using criteria query.", description = "Search product using criteria query.")
    @PostMapping(value = "/v1/searchByCriteriaQuery")
    public ResponseEntity<List<ProductResponseDto>> searchByCriteriaQuery(@RequestBody SearchRequestDto requestDto) {
        log.info("ProductController - searchByCriteriaQuery() called...");
        return ResponseEntity.ok(productService.searchByCriteriaQuery(requestDto));
    }
}
