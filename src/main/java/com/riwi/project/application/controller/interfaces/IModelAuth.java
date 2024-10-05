package com.riwi.project.application.controller.interfaces;

import com.riwi.project.application.dto.request.LoginRequestDTO;
import com.riwi.project.application.dto.request.RegisterRequestDTO;
import com.riwi.project.utils.enu.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IModelAuth {
    public ResponseEntity<?> registerUser(
            @RequestBody RegisterRequestDTO registerRequestDTO,
            @RequestParam(required = false, defaultValue = "USER") Role role);

    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO);
}
