package com.github.evertonbrunosds.shop.model.entity;

import java.util.UUID;

import com.github.evertonbrunosds.shop.util.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(schema = Schema.CURRENT)
@Entity(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = false)
    private UUID id;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "image_url", length = 512, nullable = false)
    private String imageUrl;

    @Column(name = "is_favorite", nullable = false)
    private Boolean isFavorite;

}
