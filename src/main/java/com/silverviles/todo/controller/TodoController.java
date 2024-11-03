package com.silverviles.todo.controller;

import com.silverviles.todo.common.TodoUtil;
import com.silverviles.todo.common.constants.ErrorCodes;
import com.silverviles.todo.common.exception.TodoNotFoundException;
import com.silverviles.todo.common.template.BaseController;
import com.silverviles.todo.common.template.BaseResponse;
import com.silverviles.todo.masterService.dao.Todo;
import com.silverviles.todo.masterService.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/todo")
public class TodoController extends BaseController {
    TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<BaseResponse<Page<Todo>>> getAllTodos(
            @ModelAttribute("userId") Long userId,
            @RequestParam(required = false, defaultValue = "0") Integer sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Get all todos started for : {}", sort);
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Todo> todos = todoService.getAllTodos(userId, pageable);
            todos = TodoUtil.sortTodos(todos, sort);
            log.info("Get all todos completed for : {}", sort);
            return ResponseEntity.ok(new BaseResponse<>(todos));
        } catch (Exception e) {
            log.error("Get all todos failed for : {}", sort, e);
            return ResponseEntity.ok(new BaseResponse<>(ErrorCodes.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<BaseResponse<Todo>> getTodoById(@ModelAttribute("userId") Long userId, @RequestParam Long id) {
        log.info("Get todo by id started for : {}", id);
        try {
            return ResponseEntity.ok(new BaseResponse<>(todoService.getTodoById(id, userId)));
        } catch (Exception e) {
            log.error("Get todo by id failed for : {}", id, e);
            return ResponseEntity.ok(new BaseResponse<>(ErrorCodes.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<BaseResponse<Boolean>> save(@ModelAttribute("userId") Long userId, @RequestBody Todo todo) {
        log.info("Save todo started for : {}", todo);
        try {
            todo.setUserId(userId);
            Todo savedTodo = todoService.save(todo);
            log.info("Save todo completed for : {}", savedTodo);
            return ResponseEntity.ok(new BaseResponse<>(true));
        } catch (Exception e) {
            log.error("Save todo failed for : {}", todo, e);
            return ResponseEntity.ok(new BaseResponse<>(ErrorCodes.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<BaseResponse<Boolean>> delete(@ModelAttribute("userId") Long userId, @RequestParam Long id) {
        log.info("Delete todo for : {}", id);
        try {
            todoService.delete(id, userId);
            return ResponseEntity.ok(new BaseResponse<>(true));
        } catch (TodoNotFoundException e) {
            log.warn("Delete todo failed for : {}", id, e);
            return ResponseEntity.ok(new BaseResponse<>(ErrorCodes.TODO_NOT_FOUND));
        } catch (Exception e) {
            log.error("Delete todo failed for : {}", id, e);
            return ResponseEntity.ok(new BaseResponse<>(ErrorCodes.INTERNAL_SERVER_ERROR));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<BaseResponse<Todo>> update(@ModelAttribute("userId") Long userId, @RequestBody Todo todo) {
        log.info("Update todo started for : {}", todo);
        try {
            Todo updatedTodo = todoService.update(todo, userId);
            log.info("Update todo completed for : {}", updatedTodo);
            return ResponseEntity.ok(new BaseResponse<>(updatedTodo));
        } catch (TodoNotFoundException e) {
            log.warn("Update todo failed for : {}", todo, e);
            return ResponseEntity.ok(new BaseResponse<>(ErrorCodes.TODO_NOT_FOUND));
        } catch (Exception e) {
            log.error("Update todo failed for : {}", todo, e);
            return ResponseEntity.ok(new BaseResponse<>(ErrorCodes.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<List<Todo>>> search(@ModelAttribute("userId") Long userId, @RequestParam String query) {
        log.info("Search todo started for : {}", query);
        try {
            List<Todo> todos = todoService.search(userId, query);
            log.info("Search todo completed for : {}", query);
            return ResponseEntity.ok(new BaseResponse<>(todos));
        } catch (Exception e) {
            log.error("Search todo failed for : {}", query, e);
            return ResponseEntity.ok(new BaseResponse<>(ErrorCodes.INTERNAL_SERVER_ERROR));
        }
    }
}
