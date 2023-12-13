package com.github.evertonbrunosds.shop.model.request;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Boolean isFavorite;

}