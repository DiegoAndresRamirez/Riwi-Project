package com.riwi.project.application.controller.impl;

import com.riwi.project.application.controller.interfaces.IModelAuth;
import com.riwi.project.application.dto.request.LoginRequestDTO;
import com.riwi.project.application.dto.request.RegisterRequestDTO;
import com.riwi.project.application.service.impl.AuthService;
import com.riwi.project.utils.enu.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements  IModelAuth{

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody RegisterRequestDTO registerRequestDTO,
            @RequestParam(required = false, defaultValue = "USER") Role role) {
        try {
            if (role == null) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(authService.registerUser(registerRequestDTO, Role.USER));
            } else {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(authService.registerUser(registerRequestDTO, role));
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequestDTO));
        } catch (UsernameNotFoundException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
