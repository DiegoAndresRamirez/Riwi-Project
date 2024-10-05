package com.riwi.project.application.service.impl;

import com.riwi.project.application.dto.request.LoginRequestDTO;
import com.riwi.project.application.dto.request.RegisterRequestDTO;
import com.riwi.project.application.dto.response.LoginResponseDTO;
import com.riwi.project.application.dto.response.RegisterResponseDTO;
import com.riwi.project.application.mapper.UserMapper;
import com.riwi.project.application.service.IModel.IModelAuth;
import com.riwi.project.domain.model.User;
import com.riwi.project.infrastructure.persistence.UserRepository;
import com.riwi.project.utils.enu.Role;
import com.riwi.project.utils.enu.helpers.JWTService;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import javax.security.auth.login.CredentialNotFoundException;

@Service
public class AuthService implements IModelAuth {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Autowired
    JWTService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponseDTO registerUser(RegisterRequestDTO registerRequestDTO, Role role) {
        // Check if the user already exists
        User existingUser = userRepository.findByUsername(registerRequestDTO.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists");
        }

        User userDb= userMapper.RegisterRequestDTOToUser(registerRequestDTO);
        userDb.setRole(role);
        userDb.setEmail(registerRequestDTO.getEmail());
        userDb.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));


        // Set the role, defaulting to USER if none is provided
        userDb.setRole(role != null ? role : Role.USER);
        userDb.setEmail(registerRequestDTO.getEmail());

        // Save the new user to the database
        userRepository.save(userDb);

        // Map the saved User back to a RegisterResponseDTO
        RegisterResponseDTO registerResponse = userMapper.userToRegisterResponseDTO(userDb);
        registerResponse.setMessage("User successfully registered");
        registerResponse.setUsername(userDb.getUsername());
        registerResponse.setRole(userDb.getRole());

        return registerResponse;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        User userlogin = userRepository.findByUsername(loginRequestDTO.getUsername());

        if(userlogin == null){
            throw new UsernameNotFoundException("User not exist");
        }

        LoginResponseDTO loginResponseDTO = userMapper.loginRequestDTOTologinResponseDTO(loginRequestDTO);
        loginResponseDTO.setMessage("Bienvenido a Riwi Project");
        loginResponseDTO.setRole(userlogin.getRole());
        loginResponseDTO.setToken(jwtService.getToken(userlogin));

        return loginResponseDTO;
    }
}
