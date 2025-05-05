package com.ecomerce.api.feature.todo;

import com.ecomerce.api.feature.role.dto.RoleRequest;
import com.ecomerce.api.feature.role.dto.RoleResponse;
import com.ecomerce.api.feature.role.dto.RoleUpdateRequest;
import com.ecomerce.api.feature.todo.dto.*;
import jakarta.validation.Valid;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@Slf4j
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createTodo(@Valid @RequestBody TodoRequest todoRequest) {

        return todoService.createTodo(todoRequest);

    }

    @GetMapping
    public List<TodoResponse> getAllTodos(@RequestParam String status) {

        return todoService.getAllTodos(status);
    }

    @GetMapping("/{uuid}")
    public TodoResponse getTodoByUuid(@PathVariable String uuid) {

        return todoService.getTodoByUuid(uuid);
    }

    @PatchMapping("/{uuid}")
    public TodoResponse updateTodoByUuid(@PathVariable String uuid,
                                         @Valid @RequestBody TodoUpdateRequest todoUpdateRequest) {

        return todoService.updateTodoByUuid(uuid, todoUpdateRequest);
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodoByUuid(@PathVariable String uuid) {

        todoService.deleteTodoByUuid(uuid);
    }

    @PutMapping("/{uuid}/done/toggle")
    public TodoResponse toggleTodoIsDoneByUuid(@PathVariable String uuid) {

      return  todoService.toggleIsDone(uuid);
    }

    @GetMapping("/counts")
    public TodoCountResponse getTodoCount(){
        return todoService.getTodoCounts();
    }

    @GetMapping("/counts/done")
    public TodoDoneResponse getTodoCountMyDayIsDone(@RequestParam String section){
        return todoService.getIsDone(section);

    }

    @GetMapping("/my-day/date")
    public MyDayDateResponse getTodoMyDayDate(){
        return todoService.getMyDayDate();

    }

    @PutMapping("/{uuid}/important/toggle")
    public TodoResponse toggleTodoIsImportantByUuid(@PathVariable String uuid) {

        return  todoService.toggleIsImportant(uuid);
    }

    @GetMapping("/search")
    public TodoSearchResponse searchTodos(@RequestParam String title) {

        return  todoService.getSearch(title);
    }
}
