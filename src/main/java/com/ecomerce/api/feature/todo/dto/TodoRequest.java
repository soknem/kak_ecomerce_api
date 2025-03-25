package com.ecomerce.api.feature.todo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TodoRequest(

        @NotBlank(message = "title is require")
        String title,

        String description,

        LocalDate deadLine,

        Boolean isMyDay,

        Boolean isImportant
) {
}
