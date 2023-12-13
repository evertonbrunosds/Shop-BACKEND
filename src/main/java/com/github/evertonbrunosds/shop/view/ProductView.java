package com.github.evertonbrunosds.shop.view;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.evertonbrunosds.shop.controller.ProductController;
import com.github.evertonbrunosds.shop.model.entity.ProductEntity;
import com.github.evertonbrunosds.shop.model.request.ProductRequest;
import com.github.evertonbrunosds.shop.model.response.ProductResponse;
import com.github.evertonbrunosds.shop.util.Route;
import com.github.evertonbrunosds.shop.util.Validator;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = Route.API.PRODUCT)
public class ProductView {

    private final ProductController controller;

    private final ModelMapper mapper;

    @PostMapping
    public ProductResponse post(final @RequestBody ProductRequest request) {
        Validator.validate(request);
        final var entityBeforeSave = mapper.map(request, ProductEntity.class);
        final var entityAfterSave = controller.post(entityBeforeSave);
        final var response = mapper.map(entityAfterSave, ProductResponse.class);
        return response;
    }

}
