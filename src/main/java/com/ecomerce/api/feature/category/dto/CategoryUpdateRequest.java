package com.ecomerce.api.feature.category.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryUpdateRequest(

        String name,

        String alias,

        String description
) {
}
