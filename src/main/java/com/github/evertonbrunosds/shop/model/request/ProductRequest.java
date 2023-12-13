package com.github.evertonbrunosds.shop.model.request;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank
    @Size(max = 64)
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @PositiveOrZero
    private Double price;

    @NotBlank
    @Size(max = 512)
    @URL
    private String imageUrl;

    @NotBlank
    private Boolean isFavorite;

}