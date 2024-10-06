package com.riwi.project.application.service.impl;

import com.riwi.project.application.dto.response.TaskCreateDTO;
import com.riwi.project.application.service.IModel.IModelUser;
import com.riwi.project.domain.model.Task;
import com.riwi.project.domain.model.User;
import com.riwi.project.infrastructure.persistence.TaskRepository;
import com.riwi.project.infrastructure.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IModelUser  {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<TaskCreateDTO> getAllTasksByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No hay una sesión activa");
        }

        Object principal = authentication.getPrincipal();
        if (principal == null) {
            throw new IllegalStateException("No hay un usuario autenticado");
        }

        String username = (String) principal;

        User user = userRepository.findByUsername(username);

        List<Task> tasks = taskRepository.findAllByUser(user);
        return tasks.stream()
                .map(task -> TaskCreateDTO.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .build())
                .collect(Collectors.toList());

    }

}
