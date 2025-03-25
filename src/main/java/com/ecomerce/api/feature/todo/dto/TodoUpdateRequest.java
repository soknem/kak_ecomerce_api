package com.ecomerce.api.feature.todo.dto;

import java.time.LocalDate;

public record TodoUpdateRequest(

        String title,

        String description,

        LocalDate dateLine
        ) {
}
