package com.riwi.project.application.mapper;

import com.riwi.project.application.dto.request.RegisterRequestDTO;
import com.riwi.project.application.dto.response.RegisterResponseDTO;
import com.riwi.project.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User RegisterRequestDTOToUser(RegisterRequestDTO registerRequestDTO);

    RegisterResponseDTO userToRegisterResponseDTO(User user);
}
