package com.ecomerce.api.feature.todo.dto;

import java.util.List;

public record TodoSearchResponse(
        Integer total,
        List<TodoResponse> data
) {
}
