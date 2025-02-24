package com.ecomerce.api.feature.role.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RoleRequest(

        @NotBlank(message = "roleName is require")
        String roleName
) {
}
