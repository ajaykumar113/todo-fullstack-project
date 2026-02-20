package com.Ajay.todo.service;

import com.Ajay.todo.model.Todo;
import java.util.List;

public interface TodoService {

    Todo createTodo(Todo todo);

    List<Todo> getAllTodos(Boolean completed);

    Todo updateTodo(Long id, Todo todo);

    void deleteTodo(Long id);
}
