package com.raven.spring_data_jpa_specifications.model.dto;

public record ReviewResponseDto(Long id,
                                int rating,
                                String comment) {
}
