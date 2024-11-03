package com.silverviles.todo.common.template;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silverviles.todo.common.constants.ErrorCodes;

@JsonSerialize
public record BaseResponse<T>(
        T data,
        Boolean success,
        String errorCode,
        String message
) {
    public BaseResponse(T data) {
        this(data, true, null, null);
    }

    public BaseResponse(ErrorCodes errorCode) {
        this(null, false, errorCode.getCode(), errorCode.getMessage());
    }

    public BaseResponse(T data, Boolean success, String errorCode, String message) {
        this.data = data;
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
    }
}