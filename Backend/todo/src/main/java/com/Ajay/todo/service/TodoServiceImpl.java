package com.Ajay.todo.service;

import com.Ajay.todo.model.Todo;
import com.Ajay.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Todo createTodo(Todo todo) {
        return repository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos(Boolean completed) {
        if (completed != null) {
            return repository.findByCompleted(completed);
        }
        return repository.findAll();
    }

    @Override
    public Todo updateTodo(Long id, Todo updatedTodo) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setTitle(updatedTodo.getTitle());
        todo.setDescription(updatedTodo.getDescription());
        todo.setCompleted(updatedTodo.isCompleted());

        return repository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }
}
