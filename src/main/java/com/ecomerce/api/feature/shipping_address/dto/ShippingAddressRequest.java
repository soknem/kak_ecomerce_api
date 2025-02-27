package com.ecomerce.api.feature.shipping_address.dto;

import com.ecomerce.api.domain.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

public record ShippingAddressRequest(

        @NotBlank(message = "phone is require")
        String phone,

        @NotBlank(message = "phone is require")
        String city,

        @NotBlank(message = "address is require")
        String address,

        String postCode,

        @NotBlank(message = "country is require")
        String country,

        @NotBlank(message = "uuid is require")
        String userUuid
) {
}
