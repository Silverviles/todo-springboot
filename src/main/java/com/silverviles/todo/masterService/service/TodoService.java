package com.silverviles.todo.masterService.service;

import com.silverviles.todo.masterService.dao.Todo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TodoService {
    Page<Todo> getAllTodos(Long id, org.springframework.data.domain.Pageable pageable);

    Todo getTodoById(Long id, Long userId);

    Todo save(Todo todo);

    void delete(Long id, Long userId);

    Todo update(Todo todo, Long userId);

    List<Todo> search(Long userId, String query);
}
