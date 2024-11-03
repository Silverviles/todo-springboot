package com.silverviles.todo.security.service;

import com.silverviles.todo.masterService.dao.User;
import com.silverviles.todo.masterService.service.UserService;
import com.silverviles.todo.security.dto.JWTRequest;
import com.silverviles.todo.security.dto.JWTResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public JWTResponse authenticate(JWTRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword())
        );

        return new JWTResponse(jwtService.generateToken(userService.findByEmail(request.getEmail())));
    }

    @Override
    @Transactional
    public JWTResponse register(JWTRequest request) throws DataIntegrityViolationException {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.saveUser(user);

        return new JWTResponse(jwtService.generateToken(user));
    }
}
