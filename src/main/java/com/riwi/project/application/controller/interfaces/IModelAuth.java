package com.riwi.project.application.controller.interfaces;

import com.riwi.project.application.dto.request.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;

public interface IModelAuth {
    ResponseEntity<?> registerUser(RegisterRequestDTO registerRequestDTO);
}
