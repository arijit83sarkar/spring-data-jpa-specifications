package com.raven.spring_data_jpa_specifications.service;

import com.raven.spring_data_jpa_specifications.model.dto.ProductRequestDto;
import com.raven.spring_data_jpa_specifications.model.dto.ProductResponseDto;
import com.raven.spring_data_jpa_specifications.model.dto.ReviewResponseDto;
import com.raven.spring_data_jpa_specifications.model.entity.Product;
import com.raven.spring_data_jpa_specifications.model.entity.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductHelperService {
    public static Product getProduct(ProductRequestDto productRequest) {
        return new Product(productRequest.name(),
                productRequest.description(),
                productRequest.price(),
                productRequest.available(),
                productRequest.manufacturer());
    }

    public static List<Review> getReviews(ProductRequestDto productRequest, Product product) {
        List<Review> reviewList = new ArrayList<>();
        productRequest.reviewRequestDtos().forEach(r -> {
            Review review = new Review(r.rating(), r.comment());
            review.setProduct(product);
            reviewList.add(review);
        });
        return reviewList;
    }

    public static ProductResponseDto getResponse(Product pReq) {
        return new ProductResponseDto(pReq.getId(),
                pReq.getName(),
                pReq.getDescription(),
                pReq.getPrice(),
                pReq.isAvailable(),
                pReq.getManufacturer(),
                pReq.getReviews().stream()
                        .map(r -> new ReviewResponseDto(r.getId(),
                                r.getRating(),
                                r.getComment()))
                        .toList());
    }

    public static List<ProductResponseDto> getResponse(List<Product> pReq) {
        return pReq.stream()
                .map(product -> new ProductResponseDto(product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.isAvailable(),
                        product.getManufacturer(),
                        product.getReviews().stream()
                                .map(r -> new ReviewResponseDto(r.getId(),
                                        r.getRating(),
                                        r.getComment())
                                ).toList())
                ).toList();
    }
}
