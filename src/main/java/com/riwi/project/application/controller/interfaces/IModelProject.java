package com.riwi.project.application.controller.interfaces;

import com.riwi.project.application.dto.request.ProjectRequestDTO;
import org.springframework.http.ResponseEntity;

public interface IModelProject {
    public ResponseEntity<?> createProjectWithTask(ProjectRequestDTO projectRequestDTO);
}
