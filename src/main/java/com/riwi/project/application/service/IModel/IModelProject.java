package com.riwi.project.application.service.IModel;

import com.riwi.project.application.dto.request.ProjectRequestDTO;
import com.riwi.project.domain.model.Project;

public interface IModelProject {

    public Project createProjectWithTask(ProjectRequestDTO projectRequestDTO);
}
