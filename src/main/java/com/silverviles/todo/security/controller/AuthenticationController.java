package com.silverviles.todo.security.controller;

import com.silverviles.todo.common.constants.ErrorCodes;
import com.silverviles.todo.common.template.BaseResponse;
import com.silverviles.todo.security.dto.JWTRequest;
import com.silverviles.todo.security.dto.JWTResponse;
import com.silverviles.todo.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<JWTResponse>> login(@RequestBody JWTRequest request) {
        try {
            return new ResponseEntity<>(new BaseResponse<>(authenticationService.authenticate(request)), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Authentication failed", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<JWTResponse>> register(@RequestBody JWTRequest request) {
        try {
            return new ResponseEntity<>(new BaseResponse<>(authenticationService.register(request)), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            log.error("Registration failed", e);
            return new ResponseEntity<>(new BaseResponse<>(ErrorCodes.USER_ALREADY_EXISTS), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Registration failed", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
