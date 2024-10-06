package com.riwi.project.application.service.impl;

import com.riwi.project.application.dto.request.ProjectRequestDTO;
import com.riwi.project.application.dto.response.ProjectCreateDTO;
import com.riwi.project.application.dto.response.TaskCreateDTO;
import com.riwi.project.application.service.IModel.IModelProject;
import com.riwi.project.domain.model.Project;
import com.riwi.project.domain.model.Task;
import com.riwi.project.domain.model.User;
import com.riwi.project.infrastructure.persistence.ProjectRepository;
import com.riwi.project.infrastructure.persistence.TaskRepository;
import com.riwi.project.infrastructure.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService implements IModelProject {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public ProjectCreateDTO createProjectWithTask(ProjectRequestDTO projectRequestDTO) {

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

            Project project = new Project();
            project.setTitle(projectRequestDTO.getProject());
            project.setDescription(projectRequestDTO.getDescription());

            List<Task> tasks = projectRequestDTO.getTasks().stream()
                    .map(taskRequest -> {
                        Task task = new Task();
                        task.setTitle(taskRequest.getTitle());
                        task.setDescription(taskRequest.getDescription());
                        task.setProject(project);
                        task.setUser(user);
                        return task;
                    })
                    .toList();

            project.setTask(new HashSet<>(tasks)); // Asignar tareas al proyecto
            projectRepository.save(project);

        Set<TaskCreateDTO> taskCreateDTOs = tasks.stream()
                .map(task -> TaskCreateDTO.builder()
                        .id(task.getId()) // Debes tener acceso al id de la tarea aquí
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .build())
                .collect(Collectors.toSet());


        ProjectCreateDTO projectCreateDTO = ProjectCreateDTO.builder()
                .id(project.getId())
                .title(project.getTitle())
                .description(project.getDescription())
                .task(taskCreateDTOs)
                .build();
        return projectCreateDTO;
    }
}

