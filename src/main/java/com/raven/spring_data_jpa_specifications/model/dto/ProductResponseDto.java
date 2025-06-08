package com.raven.spring_data_jpa_specifications.model.dto;

import java.util.List;

public record ProductResponseDto(Long id,
                                 String name,
                                 String description,
                                 double price,
                                 boolean available,
                                 String manufacturer,
                                 List<ReviewResponseDto> reviews) {
}
