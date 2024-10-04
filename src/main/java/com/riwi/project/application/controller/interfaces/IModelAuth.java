package com.riwi.project.application.controller.interfaces;

import com.riwi.project.application.dto.request.RegisterRequestDTO;
import com.riwi.project.utils.enu.Role;
import org.springframework.http.ResponseEntity;

public interface IModelAuth {
    ResponseEntity<?> registerUser(RegisterRequestDTO registerRequestDTO, Role admin);

    ResponseEntity<?> registerUserRegular(RegisterRequestDTO registerRequestDTO, Role user);
}
