package com.ecomerce.api.feature.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AuthRequest(

        @NotBlank(message = "Email or username is required")
        String emailOrUsername,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d\\s]).{8,}$",
                message = "Password must be strong password with at least one uppercase, one lowercase, one digit and one special character")
        String password
) {
}
