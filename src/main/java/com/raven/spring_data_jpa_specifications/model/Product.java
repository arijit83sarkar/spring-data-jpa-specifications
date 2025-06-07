package com.raven.spring_data_jpa_specifications.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    public Product() {
    }

    public Product(String name, String description, double price, boolean available, String manufacturer) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.manufacturer = manufacturer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", manufacturer='" + manufacturer + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
