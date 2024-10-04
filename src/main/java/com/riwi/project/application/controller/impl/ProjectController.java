package com.riwi.project.application.controller.impl;

import com.riwi.project.application.dto.request.ProjectRequestDTO;
import com.riwi.project.application.controller.interfaces.IModelProject;
import com.riwi.project.application.service.impl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController implements IModelProject {

    @Autowired
    ProjectService projectService;

    @PostMapping("/create-project")
    @Override
    public ResponseEntity<?> createProjectWithTask(@RequestBody ProjectRequestDTO projectRequestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProjectWithTask(projectRequestDTO));
    }
}
