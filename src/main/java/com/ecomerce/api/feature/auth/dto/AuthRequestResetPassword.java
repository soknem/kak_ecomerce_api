package com.ecomerce.api.feature.auth.dto;

import jakarta.validation.constraints.Pattern;

public record AuthRequestResetPassword (

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d\\s]).{8,}$",
                message = "Password must be strong password with at least one uppercase, one lowercase, one digit and one special character")
        String newPassword,

        String confirmPassword
) {
}