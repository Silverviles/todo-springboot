package com.silverviles.todo.common.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException() {
        super("Error with todo operation: Todo not found");
    }
}
