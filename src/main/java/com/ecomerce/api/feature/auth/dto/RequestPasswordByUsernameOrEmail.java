package com.ecomerce.api.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestPasswordByUsernameOrEmail(

        @NotBlank( message = "Username or email is required")
        String usernameOrEmail
) {
}
