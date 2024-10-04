package com.riwi.project.application.controller.impl;

import com.riwi.project.application.dto.request.ProjectRequestDTO;
import com.riwi.project.application.controller.interfaces.IModelProject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController implements IModelProject {
    @Override
    public ResponseEntity<?> createProjectWithTask(ProjectRequestDTO projectRequestDTO) {
        return null;
    }
}
