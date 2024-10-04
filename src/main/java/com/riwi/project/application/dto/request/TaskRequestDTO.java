package com.riwi.project.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TaskRequestDTO {

    private String title;

    private String description;
}
