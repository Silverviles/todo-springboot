package com.silverviles.todo.masterService.dao;

import com.silverviles.todo.common.config.EncryptionUtil;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Long id;
    @ToString.Include
    private Long userId;
    @ToString.Include
    @Convert(converter = EncryptionUtil.class)
    private String title;
    @Convert(converter = EncryptionUtil.class)
    private String description;
    private String createdDate;
    private String dueDate;
    private boolean isDone;
}
