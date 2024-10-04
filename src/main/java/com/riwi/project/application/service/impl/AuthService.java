package com.riwi.project.application.service.impl;

import com.riwi.project.application.dto.request.RegisterRequestDTO;
import com.riwi.project.application.dto.response.RegisterResponseDTO;
import com.riwi.project.application.service.IModel.IModelAuth;
import com.riwi.project.utils.enu.Role;

public class AuthService implements IModelAuth {
    @Override
    public RegisterResponseDTO registerUser(RegisterRequestDTO registerRequestDTO, Role role) {


        return null;
    }
}
