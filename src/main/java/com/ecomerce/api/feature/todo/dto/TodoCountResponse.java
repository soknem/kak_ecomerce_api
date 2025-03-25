package com.ecomerce.api.feature.todo.dto;

public record TodoCountResponse (
        Integer myDay,
        Integer important,
        Integer planned,
        Integer assigned,
        Integer work
        ){

}
