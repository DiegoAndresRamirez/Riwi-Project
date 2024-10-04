package com.riwi.project.application.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegisterRequestDTO {

    private String username;
    private String password;
    private String email;
}
