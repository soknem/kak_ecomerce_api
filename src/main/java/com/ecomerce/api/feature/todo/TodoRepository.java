package com.ecomerce.api.feature.todo;

import com.ecomerce.api.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    Optional<Todo> findByUuid(String uuid);

    //count my done
    Integer countByIsMyDayAndIsDone(Boolean isMyDay, Boolean isDone);

    //count is done task and work task
    Integer countByIsDone(Boolean isDone);

    //count important task
    Integer countByIsImportantAndIsDone(Boolean isImportant,Boolean isDone);

    //count planned task
    Integer countByIsDoneAndDeadLineNot(Boolean isDone,LocalDate deadLine);

    //get all my day
    List<Todo> findByIsMyDayAndIsDone(Boolean isMyDay, Boolean isDone);

    //get all done task and work task
    List<Todo> findByIsDone(Boolean isDone);

    //get all important task
    List<Todo> findByIsImportantAndIsDone(Boolean isImportant,Boolean isDone);

    //get all planned task
    List<Todo> findByIsDoneAndDeadLine(Boolean isDone,LocalDate deadLine);

    List<Todo> findByDeadLine(LocalDate date);

    List<Todo> findByTitleContains(String title);


}
