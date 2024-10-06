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
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IModelAuth {

    @Autowired
    UserRepository userRepository;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired
    JWTService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponseDTO registerUser(RegisterRequestDTO registerRequestDTO, Role role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication: " + authentication);
        System.out.println("Is authenticated: " + (authentication != null && authentication.isAuthenticated()));
        if (authentication != null) {
            System.out.println("Authorities: " + authentication.getAuthorities());
        }
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();

        if (isAuthenticated && authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Role.USER.name()))) {
            throw new IllegalArgumentException("Logged in users cannot register new accounts");
        }

        User existingUser = userRepository.findByUsername(registerRequestDTO.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists");
        }

        User userDb = userMapper.RegisterRequestDTOToUser(registerRequestDTO);
        userDb.setEmail(registerRequestDTO.getEmail());
        userDb.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

        if (role == Role.ADMIN) {
            if (!isAuthenticated || !authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Role.ADMIN.name()))) {
                throw new IllegalArgumentException("Only admins can register new admins");
            }
            userDb.setRole(Role.ADMIN);
        } else if (role == Role.USER) {
            userDb.setRole(Role.USER);
        } else {
            throw new IllegalArgumentException("Invalid role specified");
        }

        userRepository.save(userDb);

        RegisterResponseDTO registerResponse = userMapper.userToRegisterResponseDTO(userDb);
        registerResponse.setMessage("User successfully registered");
        registerResponse.setUsername(userDb.getUsername());
        registerResponse.setRole(userDb.getRole());

        return registerResponse;
    }


    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuth != null && currentAuth.isAuthenticated() &&
                !(currentAuth instanceof AnonymousAuthenticationToken)) {
            throw new IllegalStateException("Ya hay una sesión activa. Por favor, cierre sesión antes de iniciar una nueva.");
        }

        User userlogin = userRepository.findByUsername(loginRequestDTO.getUsername());

        if (userlogin == null) {
            throw new UsernameNotFoundException("El usuario no existe");
        }

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), userlogin.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        LoginResponseDTO loginResponseDTO = userMapper.loginRequestDTOTologinResponseDTO(loginRequestDTO);
        loginResponseDTO.setMessage("Bienvenido a Riwi Project");
        loginResponseDTO.setRole(userlogin.getRole());
        loginResponseDTO.setToken(jwtService.getToken(userlogin));

        return loginResponseDTO;
    }


}
