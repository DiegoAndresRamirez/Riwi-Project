package com.riwi.project.application.dto.response;

import com.riwi.project.utils.enu.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginResponseDTO {

    private String message;

    private Role role;

    private String token;
}
