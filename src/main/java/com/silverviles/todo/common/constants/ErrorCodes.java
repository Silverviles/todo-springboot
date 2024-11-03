package com.silverviles.todo.common.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {
    INTERNAL_SERVER_ERROR("Internal server error"),
    USER_NOT_FOUND("User not found"),
    USER_ALREADY_EXISTS("User already exists"),
    INVALID_CREDENTIALS("Invalid credentials"),
    TODO_NOT_FOUND("Todo not found");

    private final String message;

    ErrorCodes(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }
}
