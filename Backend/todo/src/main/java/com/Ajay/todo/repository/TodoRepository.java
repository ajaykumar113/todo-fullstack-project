package com.Ajay.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Ajay.todo.model.Todo;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByCompleted(boolean completed);

}
