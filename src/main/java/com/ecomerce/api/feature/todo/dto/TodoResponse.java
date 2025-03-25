package com.ecomerce.api.feature.todo.dto;

import java.time.LocalDate;

public record TodoResponse(

        String uuid,

        String title,

        LocalDate dateLine,

        Boolean isDone

        ) {
}
