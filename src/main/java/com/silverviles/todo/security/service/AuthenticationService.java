package com.silverviles.todo.security.service;

import com.silverviles.todo.security.dto.JWTRequest;
import com.silverviles.todo.security.dto.JWTResponse;
import org.springframework.dao.DataIntegrityViolationException;

public interface AuthenticationService {
    JWTResponse authenticate(JWTRequest request);

    JWTResponse register(JWTRequest request) throws DataIntegrityViolationException;
}
