package com.silverviles.todo.common.config;

import com.silverviles.todo.common.template.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = {BaseController.class})
public class BusinessLogicControllerAdvice {
    @ModelAttribute("userId")
    public Long addUserIdToModel(HttpServletRequest request) {
        return (Long) request.getAttribute("userId");
    }
}
