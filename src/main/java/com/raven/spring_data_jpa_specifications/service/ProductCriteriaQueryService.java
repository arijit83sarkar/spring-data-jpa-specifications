package com.raven.spring_data_jpa_specifications.service;

import com.raven.spring_data_jpa_specifications.model.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCriteriaQueryService {
    private static final Logger log = LoggerFactory.getLogger(ProductCriteriaQueryService.class);
    private final EntityManager entityManager;

    @Autowired
    public ProductCriteriaQueryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> nameLike(String name) {
        log.info("ProductCriteriaQueryService - nameLike() called ....");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        Predicate predicateLike = criteriaBuilder.like(root.get("name"), "%" + name + "%");
        criteriaQuery.select(root).where(predicateLike);
        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }
}
