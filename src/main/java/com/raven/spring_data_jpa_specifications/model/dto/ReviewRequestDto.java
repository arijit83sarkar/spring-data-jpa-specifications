package com.raven.spring_data_jpa_specifications.model.dto;

public record ReviewRequestDto(int rating,
                               String comment) {
}
