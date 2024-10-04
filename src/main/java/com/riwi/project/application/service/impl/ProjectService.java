package com.riwi.project.application.service.impl;

import com.riwi.project.application.dto.request.ProjectRequestDTO;
import com.riwi.project.application.dto.response.ProjectCreateDTO;
import com.riwi.project.application.dto.response.TaskCreateDTO;
import com.riwi.project.application.service.IModel.IModelProject;
import com.riwi.project.domain.model.Project;
import com.riwi.project.domain.model.Task;
import com.riwi.project.infrastructure.persistence.ProjectRepository;
import com.riwi.project.infrastructure.persistence.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public ProjectCreateDTO createProjectWithTask(ProjectRequestDTO projectRequestDTO) {
            Project project = new Project();
            project.setTitle(projectRequestDTO.getProject());
            project.setDescription(projectRequestDTO.getDescription());

            List<Task> tasks = projectRequestDTO.getTasks().stream()
                    .map(taskRequest -> {
                        Task task = new Task();
                        task.setTitle(taskRequest.getTitle());
                        task.setDescription(taskRequest.getDescription());
                        task.setProject(project); // Asignar el proyecto a la tarea
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

