package com.ecomerce.api.feature.todo;

import com.ecomerce.api.domain.Todo;
import com.ecomerce.api.feature.todo.dto.*;
import com.ecomerce.api.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoMapper todoMapper;
    private final TodoRepository todoRepository;

    @Override
    public TodoResponse createTodo(TodoRequest todoRequest) {

        Todo todo = todoMapper.fromRequest(todoRequest);

        todo.setUuid(UUID.randomUUID().toString());
        todo.setIsDone(false);

        todoRepository.save(todo);

        return todoMapper.toTodoResponse(todo);
    }

    @Override
    public TodoResponse getTodoByUuid(String uuid) {

        Todo todo =
                todoRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("todo = uuid %s has not been found", uuid)));
        return todoMapper.toTodoResponse(todo);
    }

    @Override
    public List<TodoResponse> getAllTodos(String status) {

        List<Todo> todos=new ArrayList<>();
         if (Objects.equals(status, "myDay")) {
                todos=todoRepository.findByIsMyDayAndIsDone(true,false);
        }else if(Objects.equals(status, "important")){

        }else if(Objects.equals(status, "planned")){

        }else if(Objects.equals(status, "assigned")){

        }else if(Objects.equals(status, "task")){
             todos=todoRepository.findByIsDone(false);
        }
         else if(Objects.equals(status, "taskDone")){
            todos=todoRepository.findByIsDone(true);
         }else{
            todos=todoRepository.findAll();
        }



        return todos.stream().map(todoMapper::toTodoResponse).toList();
    }

    @Override
    public TodoResponse updateTodoByUuid(String uuid, TodoUpdateRequest todoUpdateRequest) {
        Todo todo =
                todoRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("todo = uuid %s has not been found", uuid)));

        todoMapper.updateTodoFromUpdateRequest(todo, todoUpdateRequest);
        todoRepository.save(todo);
        return todoMapper.toTodoResponse(todo);
    }

    @Override
    public void deleteTodoByUuid(String uuid) {
        Todo todo =
                todoRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("todo = uuid %s has not been found", uuid)));
        todoRepository.delete(todo);
    }

    @Override
    public TodoResponse toggleIsDone(String uuid) {
        Todo todo =
                todoRepository.findByUuid(uuid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("todo = uuid %s has not been found", uuid)));

        todo.setIsDone(!todo.getIsDone());

        todoRepository.save(todo);

        return todoMapper.toTodoResponse(todo);
    }

    @Override
    public TodoCountResponse getTodoCounts() {

        Integer myDay = todoRepository.countByIsMyDayAndIsDone(true,false);
        Integer important=todoRepository.countByIsImportantAndIsDone(true,false);
        Integer planned =todoRepository.countByIsDoneAndDeadLineNot(false,null);
        Integer assigned =0;
        Integer work = todoRepository.countByIsDone(false);
        return new TodoCountResponse(myDay,important,planned,assigned,work);
    }

    @Override
    public TodoDoneResponse getIsDone(String section) {

        Integer doneCount=0;
        if(Objects.equals(section, "myDay")){
            doneCount = todoRepository.countByIsMyDayAndIsDone(true,true);
        }else if(Objects.equals(section, "workDone")){
            doneCount=todoRepository.countByIsDone(true);
        }

        return new TodoDoneResponse(doneCount);
    }

    @Override
    public MyDayDateResponse getMyDayDate() {

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd", new java.util.Locale("km", "KH"));
        String formattedDate = today.format(formatter);

        return new MyDayDateResponse(formattedDate);
    }
}
