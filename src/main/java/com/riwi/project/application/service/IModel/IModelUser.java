package com.riwi.project.application.service.IModel;

import com.riwi.project.application.dto.response.TaskCreateDTO;

import java.util.List;

public interface IModelUser {
    List<TaskCreateDTO> getAllTasksByUser();
}
