package com.ecomerce.api.feature.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserResponse(

        String uuid,
        String username,

        String password,

        String email,

        String phone,

        String avatar,

        String address

) {
}
