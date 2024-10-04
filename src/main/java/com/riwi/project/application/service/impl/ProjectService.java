package com.riwi.project.application.service.impl;

import com.riwi.project.application.dto.request.ProjectRequestDTO;
import com.riwi.project.application.service.IModel.IModelProject;
import com.riwi.project.domain.model.Project;
import com.riwi.project.domain.model.Task;
import com.riwi.project.infrastructure.persistence.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ProjectService implements IModelProject {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Project createProjectWithTask(ProjectRequestDTO projectRequestDTO) {
            Project project = new Project();
            project.setTitle(projectRequestDTO.getProject());
            project.setDescription(projectRequestDTO.getDescription());

            List<Task> tasks = projectRequestDTO.getTaskList().stream()
                    .map(taskRequest -> {
                        Task task = new Task();
                        task.setTitle(taskRequest.getTitle());
                        task.setDescription(taskRequest.getDescription());
                        task.setProject(project); // Asignar el proyecto a la tarea
                        return task;
                    })
                    .toList();

            project.setTask(new HashSet<>(tasks)); // Asignar tareas al proyecto
            return projectRepository.save(project); // Guardar el proyecto y las tareas
        }

}

