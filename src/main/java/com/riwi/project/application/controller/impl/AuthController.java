package com.riwi.project.application.controller.impl;

import com.riwi.project.application.controller.interfaces.IModelAuth;
import com.riwi.project.application.dto.request.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;

public class AuthController implements IModelAuth {
    @Override
    public ResponseEntity<?> registerUser(RegisterRequestDTO registerRequestDTO) {
        return null;
    }
}
