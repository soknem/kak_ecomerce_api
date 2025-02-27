package com.ecomerce.api.feature.product.dto;

public record ProductUpdateRequest(

        String name,

        String description,

        Integer qty,

        Double price,

        String image
) {
}
