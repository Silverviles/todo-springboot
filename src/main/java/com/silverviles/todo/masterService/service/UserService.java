package com.silverviles.todo.masterService.service;

import com.silverviles.todo.masterService.dao.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void saveUser(User user);

    User findByEmail(String email);

    User findById(Long id);

    void deleteUser(User user);

    void updateUser(User user);
}
