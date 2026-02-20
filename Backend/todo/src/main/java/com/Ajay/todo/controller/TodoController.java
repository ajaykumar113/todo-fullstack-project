package com.Ajay.todo.controller;

import com.Ajay.todo.model.Todo;
import com.Ajay.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    // Create Todo
    @PostMapping
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        return service.createTodo(todo);
    }

    // Get All Todos (Optional filter)
    @GetMapping
    public List<Todo> getAllTodos(@RequestParam(required = false) Boolean completed) {
        return service.getAllTodos(completed);
    }

    // Update Todo
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        return service.updateTodo(id, todo);
    }

    // Delete Todo
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        service.deleteTodo(id);
    }
}
