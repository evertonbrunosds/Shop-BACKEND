package com.github.evertonbrunosds.shop.model.request;

import static com.github.evertonbrunosds.shop.util.Constraint.ifBlank;
import static com.github.evertonbrunosds.shop.util.Constraint.ifNotURL;
import static com.github.evertonbrunosds.shop.util.Constraint.ifNull;
import static com.github.evertonbrunosds.shop.util.Constraint.ifTrue;
import static com.github.evertonbrunosds.shop.util.Converter.convertTo;
import static com.github.evertonbrunosds.shop.util.Parameter.DESCRIPTION;
import static com.github.evertonbrunosds.shop.util.Parameter.IMAGE_URL;
import static com.github.evertonbrunosds.shop.util.Parameter.IS_FAVORITE;
import static com.github.evertonbrunosds.shop.util.Parameter.NAME;
import static com.github.evertonbrunosds.shop.util.Parameter.PRICE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import com.github.evertonbrunosds.shop.util.ResourceException;

import lombok.Setter;

@Setter
public class ProductRequest {

    private String name;

    private String description;

    private String price;

    private String imageUrl;

    private String isFavorite;

    public String getName() {
        ifBlank(name).throwResourceException(BAD_REQUEST, NAME, getClass());
        ifTrue(name.length() > 64).throwResourceException(FORBIDDEN, NAME, getClass());
        return name;
    }

    public String getDescription() {
        ifBlank(description).throwResourceException(BAD_REQUEST, DESCRIPTION, getClass());
        return description;
    }

    public Double getPrice() {
        ifNull(price).throwResourceException(BAD_REQUEST, PRICE, getClass());
        final var convertedPrice = convertTo(() -> Double.parseDouble(price)).orThrow(() -> {
            return new ResourceException(BAD_REQUEST, PRICE, getClass());
        });
        ifTrue(convertedPrice < 0).throwResourceException(FORBIDDEN, PRICE, getClass());
        return convertedPrice;
    }

    public String getImageUrl() {
        ifBlank(imageUrl).throwResourceException(BAD_REQUEST, IMAGE_URL, getClass());
        ifNotURL(imageUrl).throwResourceException(FORBIDDEN, IMAGE_URL, getClass());
        ifTrue(imageUrl.length() > 512).throwResourceException(FORBIDDEN, IMAGE_URL, getClass());
        return imageUrl;
    }

    public Boolean getIsFavorite() {
        ifNull(isFavorite).throwResourceException(FORBIDDEN, IS_FAVORITE, getClass());
        return convertTo(() -> {
            switch (isFavorite.toLowerCase()) {
                case "true":
                    return true;
                case "false":
                    return false;
                default:
                    throw new IllegalArgumentException();
            }
        }).orThrow(() -> {
            return new ResourceException(BAD_REQUEST, IS_FAVORITE, getClass());
        });
    }

}
