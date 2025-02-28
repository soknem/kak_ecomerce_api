package com.ecomerce.api.feature.cart.dto;

public record CartResponse(

        String uuid,
        Integer qty,

        String productName
) {
}
