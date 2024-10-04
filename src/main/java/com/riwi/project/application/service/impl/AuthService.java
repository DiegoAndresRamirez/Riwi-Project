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

        User user = userRepository.findByUsername(registerRequestDTO.getUsername());

        if(user != null){
            throw new IllegalArgumentException("user exist");
        }

        User userDb= userMapper.RegisterRequestDTOToUser(registerRequestDTO);
        userDb.setRole(role);
        userDb.setEmail(registerRequestDTO.getEmail());

        userRepository.save(userDb);

        RegisterResponseDTO registeResponse =  userMapper.userToRegisterResponseDTO(userDb);
        registeResponse.setMessage("User Successfully");
        registeResponse.setUsername(userDb.getUsername());
        registeResponse.setRole(userDb.getRole());

        return registeResponse;

    }
}
