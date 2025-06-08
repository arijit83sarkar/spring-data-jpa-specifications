package com.raven.spring_data_jpa_specifications.model.dto;

import java.util.List;

public record ProductRequestDto(String name,
                                String description,
                                double price,
                                boolean available,
                                String manufacturer,
                                List<ReviewRequestDto> reviewRequestDtos) {
}
