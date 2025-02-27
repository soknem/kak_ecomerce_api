package com.ecomerce.api.feature.product.dto;

public record ProductResponse(

        String name,

        String uuid,

        String description,

        Integer qty,

        Double price,

        String image
) {
}
