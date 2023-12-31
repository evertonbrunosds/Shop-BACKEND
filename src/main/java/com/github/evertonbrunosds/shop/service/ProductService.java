package com.github.evertonbrunosds.shop.service;

import org.springframework.stereotype.Service;

import com.github.evertonbrunosds.shop.model.entity.ProductEntity;
import com.github.evertonbrunosds.shop.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductEntity post(final ProductEntity entity) {
        entity.setId(null);
        return repository.save(entity);
    }

}
