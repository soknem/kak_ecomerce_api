package com.ecomerce.api.feature.cart.dto;

public record CartRequest(

        Integer qty,

        String userUuid,

        String productUuid
) {
}
