package com.riwi.project.application.controller.interfaces;

import com.riwi.project.application.dto.response.TaskCreateDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IModelUser {
    ResponseEntity<List<TaskCreateDTO>> getAllTasksByUser();

}
