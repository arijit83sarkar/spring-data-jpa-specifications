package com.raven.spring_data_jpa_specifications.repository;

import com.raven.spring_data_jpa_specifications.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepo extends JpaRepository<Review, Long> {
}
