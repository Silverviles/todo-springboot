package com.silverviles.todo;

import com.silverviles.todo.common.constants.ErrorCodes;
import com.silverviles.todo.common.exception.TodoNotFoundException;
import com.silverviles.todo.common.template.BaseResponse;
import com.silverviles.todo.controller.TodoController;
import com.silverviles.todo.masterService.dao.Todo;
import com.silverviles.todo.masterService.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class TodoTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTodos() {
        Page<Todo> todos = new PageImpl<>(Collections.singletonList(new Todo()));
        when(todoService.getAllTodos(anyLong(), any(PageRequest.class))).thenReturn(todos);

        ResponseEntity<BaseResponse<Page<Todo>>> response = todoController.getAllTodos(1L, 0, 0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().data());
        assertEquals(todos, response.getBody().data());
    }

    @Test
    public void testGetTodoById() {
        Todo todo = new Todo();
        when(todoService.getTodoById(anyLong(), anyLong())).thenReturn(todo);

        ResponseEntity<BaseResponse<Todo>> response = todoController.getTodoById(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().data());
        assertEquals(todo, response.getBody().data());
    }

    @Test
    public void testSaveTodo() {
        Todo todo = new Todo();
        when(todoService.save(any(Todo.class))).thenReturn(todo);

        ResponseEntity<BaseResponse<Boolean>> response = todoController.save(1L, todo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().data());
        assertEquals(true, response.getBody().data());
    }

    @Test
    public void testDeleteTodo() {
        doNothing().when(todoService).delete(anyLong(), anyLong());

        ResponseEntity<BaseResponse<Boolean>> response = todoController.delete(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().data());
        assertEquals(true, response.getBody().data());
    }

    @Test
    public void testDeleteTodoNotFound() {
        doThrow(new TodoNotFoundException()).when(todoService).delete(anyLong(), anyLong());

        ResponseEntity<BaseResponse<Boolean>> response = todoController.delete(1L, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().errorCode());
        assertEquals(ErrorCodes.TODO_NOT_FOUND.toString(), response.getBody().errorCode());
    }

    @Test
    public void testUpdateTodo() {
        Todo todo = new Todo();
        when(todoService.update(any(Todo.class), anyLong())).thenReturn(todo);

        ResponseEntity<BaseResponse<Todo>> response = todoController.update(1L, todo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().data());
        assertEquals(todo, response.getBody().data());
    }
}