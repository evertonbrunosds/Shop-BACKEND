package com.github.evertonbrunosds.shop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.evertonbrunosds.shop.model.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

}
