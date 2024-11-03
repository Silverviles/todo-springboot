package com.silverviles.todo.security.service;

import com.silverviles.todo.masterService.dao.User;

import java.util.Map;

public interface JWTService {
    String generateToken(User user);

    String generateToken(Map<String, Object> extraClaims, String email);

    Long extractUserId(String token);

    String extractEmail(String token);

    Boolean isTokenExpired(String token);
}
