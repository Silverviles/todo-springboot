package com.silverviles.todo.masterService.repository;

import com.silverviles.todo.masterService.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
