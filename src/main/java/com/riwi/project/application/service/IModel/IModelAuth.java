package com.riwi.project.application.service.IModel;

import com.riwi.project.application.dto.request.LoginRequestDTO;
import com.riwi.project.application.dto.request.RegisterRequestDTO;
import com.riwi.project.application.dto.response.LoginResponseDTO;
import com.riwi.project.application.dto.response.RegisterResponseDTO;
import com.riwi.project.utils.enu.Role;

public interface IModelAuth {
    RegisterResponseDTO registerUser(RegisterRequestDTO registerRequestDTO, Role role);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
