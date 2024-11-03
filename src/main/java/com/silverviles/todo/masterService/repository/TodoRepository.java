package com.silverviles.todo.masterService.repository;

import com.silverviles.todo.masterService.dao.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Page<Todo> findByUserId(Long userId, Pageable pageable);
    Boolean existsByIdAndUserId(Long id, Long userId);
    Optional<Todo> findByIdAndUserId(Long id, Long userId);
    List<Todo> findByUserIdAndTitleInOrDescriptionIn(Long userId, List<String> titles, List<String> descriptions);
}
