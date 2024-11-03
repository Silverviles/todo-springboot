package com.silverviles.todo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -3497194292967765329L;
    private String email;
    private String password;
}
