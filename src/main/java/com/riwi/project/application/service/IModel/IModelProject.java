package com.riwi.project.application.service.IModel;

import com.riwi.project.application.dto.request.ProjectRequestDTO;
import com.riwi.project.application.dto.response.ProjectCreateDTO;

public interface IModelProject {

    ProjectCreateDTO createProjectWithTask(ProjectRequestDTO projectRequestDTO);
}
