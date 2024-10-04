package com.riwi.project.application.dto.request;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;



@Builder
@Getter
@Setter
public class ProjectRequestDTO {

    private String project;

    private String description;

    private List<TaskRequestDTO> tasks;
}
