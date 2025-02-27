package com.ecomerce.api.feature.shipping_address.dto;

import jakarta.validation.constraints.NotBlank;

public record ShippingAddressUpdateRequest(

        String phone,

        String city,

        String address,

        String postCode,

        String country
) {
}
