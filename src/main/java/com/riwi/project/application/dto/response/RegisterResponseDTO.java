package com.riwi.project.application.dto.response;

import com.riwi.project.utils.enu.Role;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {

    private String message;

    private String username;

    private Role role;
}
