package com.riwi.project.application.controller.impl;

import com.riwi.project.application.controller.interfaces.IModelAuth;
import com.riwi.project.application.dto.request.RegisterRequestDTO;
import com.riwi.project.application.service.impl.AuthService;
import com.riwi.project.utils.enu.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IModelAuth {

    @Autowired
    AuthService authService;

    @PostMapping("/register-admin")
    @Override
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO, Role admin) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(registerRequestDTO,Role.ADMIN));
        }catch (IllegalArgumentException e){
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/register-user")
    @Override
    public ResponseEntity<?> registerUserRegular(@RequestBody RegisterRequestDTO registerRequestDTO, Role user) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(registerRequestDTO,Role.USER));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
