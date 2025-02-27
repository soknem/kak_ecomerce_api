package com.ecomerce.api.feature.product.dto;

import com.ecomerce.api.domain.Category;
import jakarta.persistence.ManyToOne;

public record ProductRequest(

        String name,

        String description,

        Integer qty,

        Double price,

        String image,

        String categoryAlias

) {
}
