package com.github.evertonbrunosds.shop.controller;

import org.springframework.stereotype.Controller;

import com.github.evertonbrunosds.shop.model.entity.ProductEntity;
import com.github.evertonbrunosds.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService service;

    public ProductEntity post(final ProductEntity entity) {
        return service.post(entity);
    }

}
