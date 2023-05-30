package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort personServicePort;
    private final IUserRequestMapper personRequestMapper;

    @Override
    public UserResponseDto saveOwner(UserRequestDto userRequestDto) {
       User userSaved = personServicePort.saveOwner(personRequestMapper.toUser(userRequestDto));
       return personRequestMapper.toResponse(userSaved);
    }

    @Override
    public boolean isOwner(String userDni) {
        return personServicePort.isOwner(userDni);
    }
}
