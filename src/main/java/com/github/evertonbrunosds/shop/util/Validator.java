package com.github.evertonbrunosds.shop.util;

import static com.github.evertonbrunosds.shop.util.Constraint.ifBlank;
import static com.github.evertonbrunosds.shop.util.Constraint.ifNotURL;
import static com.github.evertonbrunosds.shop.util.Constraint.ifNull;
import static com.github.evertonbrunosds.shop.util.Constraint.ifTrue;
import static com.github.evertonbrunosds.shop.util.Parameter.DESCRIPTION;
import static com.github.evertonbrunosds.shop.util.Parameter.IMAGE_URL;
import static com.github.evertonbrunosds.shop.util.Parameter.IS_FAVORITE;
import static com.github.evertonbrunosds.shop.util.Parameter.NAME;
import static com.github.evertonbrunosds.shop.util.Parameter.PRICE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

import com.github.evertonbrunosds.shop.model.request.ProductRequest;

public class Validator {

    public static void validate(final ProductRequest request) {
        ifBlank(request.getName()).throwResourceException(BAD_REQUEST, NAME, request.getClass());
        ifTrue(request.getName().length() > 64).throwResourceException(FORBIDDEN, NAME, request.getClass());
        ifBlank(request.getDescription()).throwResourceException(BAD_REQUEST, DESCRIPTION, request.getClass());
        ifNull(request.getPrice()).throwResourceException(BAD_REQUEST, PRICE, request.getClass());
        ifTrue(request.getPrice() < 0).throwResourceException(FORBIDDEN, PRICE, request.getClass());
        ifBlank(request.getImageUrl()).throwResourceException(BAD_REQUEST, IMAGE_URL, request.getClass());
        ifNotURL(request.getImageUrl()).throwResourceException(FORBIDDEN, IMAGE_URL, request.getClass());
        ifTrue(request.getImageUrl().length() > 512).throwResourceException(FORBIDDEN, IMAGE_URL, request.getClass());
        ifNull(request.getIsFavorite()).throwResourceException(FORBIDDEN, IS_FAVORITE, request.getClass());
    }

}
