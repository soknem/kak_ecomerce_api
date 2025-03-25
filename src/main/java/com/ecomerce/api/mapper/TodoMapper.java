package com.ecomerce.api.mapper;

import com.ecomerce.api.domain.Todo;
import com.ecomerce.api.feature.todo.dto.TodoRequest;
import com.ecomerce.api.feature.todo.dto.TodoResponse;
import com.ecomerce.api.feature.todo.dto.TodoUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TodoMapper {

    Todo fromRequest(TodoRequest todoRequest);

    TodoResponse toTodoResponse(Todo todo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTodoFromUpdateRequest(@MappingTarget Todo todo, TodoUpdateRequest todoUpdateRequest);
}
