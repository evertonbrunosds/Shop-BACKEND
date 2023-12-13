package com.github.evertonbrunosds.shop.model.response;

import java.util.UUID;

import lombok.Data;

@Data
public class ProductResponse {

    private UUID id;

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Boolean isFavorite;

}
