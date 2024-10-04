package com.riwi.project.application.dto.response;

import com.riwi.project.domain.model.User;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TaskCreateDTO {
    private Long id;

    private String title;

    private String description;

//    private UserCreateDTO user;
}
