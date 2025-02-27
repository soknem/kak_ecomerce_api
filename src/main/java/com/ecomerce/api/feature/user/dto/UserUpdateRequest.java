package com.ecomerce.api.feature.user.dto;

public record UserUpdateRequest(

        String email,

        String phone,

        String avatar,

        String address
) {
}
