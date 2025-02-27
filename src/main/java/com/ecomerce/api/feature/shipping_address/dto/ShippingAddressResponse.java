package com.ecomerce.api.feature.shipping_address.dto;

public record ShippingAddressResponse(

        String uuid,
        String phone,

        String city,

        String address,

        String postCode,

        String country
) {
}
