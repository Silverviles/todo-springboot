package com.silverviles.todo.masterService.service;

import com.silverviles.todo.common.exception.TodoNotFoundException;
import com.silverviles.todo.masterService.dao.Todo;
import com.silverviles.todo.masterService.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Page<Todo> getAllTodos(Long id, Pageable pageable) {
        return todoRepository.findByUserId(id, pageable);
    }

    @Override
    public Todo getTodoById(Long id, Long userId) {
        return todoRepository.findByIdAndUserId(id, userId).orElse(null);
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void delete(Long id, Long userId) {
        if (!todoRepository.existsByIdAndUserId(id, userId)) {
            throw new TodoNotFoundException();
        }
        todoRepository.deleteById(id);
    }

    @Override
    public Todo update(Todo todo, Long userId) {
        if (!todoRepository.existsByIdAndUserId(todo.getId(), userId)) {
            throw new TodoNotFoundException();
        }
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> search(Long userId, String query) {
        List<String> keywords = Arrays.asList(query.split(","));
        return todoRepository.findByUserIdAndTitleInOrDescriptionIn(userId, keywords, keywords);
    }
}
