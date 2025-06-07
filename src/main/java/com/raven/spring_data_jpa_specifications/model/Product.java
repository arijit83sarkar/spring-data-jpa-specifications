package com.raven.spring_data_jpa_specifications.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Setter
@ToString
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120)
    private String name;

    @Column(length = 220)
    private String description;
    private double price;
    private boolean available;

    @Column(length = 120)
    private String manufacturer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();
}
