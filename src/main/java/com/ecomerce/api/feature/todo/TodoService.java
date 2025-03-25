package com.ecomerce.api.feature.todo;

import com.ecomerce.api.domain.Todo;
import com.ecomerce.api.feature.todo.dto.*;

import java.util.List;

public interface TodoService {

    TodoResponse createTodo(TodoRequest todoRequest);

    TodoResponse getTodoByUuid(String uuid);

    List<TodoResponse> getAllTodos(String status);

    TodoResponse updateTodoByUuid(String uuid, TodoUpdateRequest todoUpdateRequest);

    void deleteTodoByUuid(String uuid);

    TodoResponse toggleIsDone(String uuid);

    TodoCountResponse getTodoCounts();
    TodoDoneResponse getIsDone(String section);

    MyDayDateResponse getMyDayDate();

}
