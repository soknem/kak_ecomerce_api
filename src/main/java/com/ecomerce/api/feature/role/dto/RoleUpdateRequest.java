package com.ecomerce.api.feature.role.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleUpdateRequest(

        @NotBlank(message = "roleName is require")
        String roleName
) {
}
