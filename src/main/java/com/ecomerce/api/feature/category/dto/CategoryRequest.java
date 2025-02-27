package com.ecomerce.api.feature.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(

        @NotBlank(message = "name is require")
        String name,

        @NotBlank(message = "alias is require")
        String alias,

        String description
) {
}
