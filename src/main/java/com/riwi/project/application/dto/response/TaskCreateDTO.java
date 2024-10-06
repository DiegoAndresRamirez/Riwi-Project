package com.riwi.project.application.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDTO {
    private Long id;

    private String title;

    private String description;

//    private UserCreateDTO user;
}
