package com.riwi.project.application.service.impl;

import com.riwi.project.application.dto.request.RegisterRequestDTO;
import com.riwi.project.application.dto.response.RegisterResponseDTO;
import com.riwi.project.application.mapper.UserMapper;
import com.riwi.project.application.service.IModel.IModelAuth;
import com.riwi.project.domain.model.User;
import com.riwi.project.infrastructure.persistence.UserRepository;
import com.riwi.project.utils.enu.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IModelAuth {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Override
    public RegisterResponseDTO registerUser(RegisterRequestDTO registerRequestDTO, Role role) {
        // Check if the user already exists
        User existingUser = userRepository.findByUsername(registerRequestDTO.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists");
        }

        // Map the RegisterRequestDTO to a User entity
        User newUser = userMapper.RegisterRequestDTOToUser(registerRequestDTO);

        // Set the role, defaulting to USER if none is provided
        newUser.setRole(role != null ? role : Role.USER);
        newUser.setEmail(registerRequestDTO.getEmail());

        // Save the new user to the database
        userRepository.save(newUser);

        // Map the saved User back to a RegisterResponseDTO
        RegisterResponseDTO registerResponse = userMapper.userToRegisterResponseDTO(newUser);
        registerResponse.setMessage("User successfully registered");
        registerResponse.setUsername(newUser.getUsername());
        registerResponse.setRole(newUser.getRole());

        return registerResponse;
    }
}
