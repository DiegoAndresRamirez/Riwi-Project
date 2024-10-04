package com.riwi.project.application.dto.response;

import com.riwi.project.domain.model.Task;
import com.riwi.project.utils.enu.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Set;

public class UserCreateDTO {
    private Long id;

    private String username;


    private String email;


    private String password;


    private Role role;

    private Set<TaskUserDTO> task;



}
