package com.riwi.project.application.dto.response;

import com.riwi.project.domain.model.Task;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class ProjectCreateDTO {

    private Long id;
    private String title;
    private String description;
    private Set<TaskCreateDTO> task;

}
