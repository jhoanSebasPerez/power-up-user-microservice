package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.client.IRestaurantClient;
import com.pragma.powerup.usermicroservice.adapters.driving.http.client.dto.RestaurantResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.client.exceptions.RestaurantNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driving.http.client.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeeRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IEmployeeHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IEmployeeRequestMapper;
import com.pragma.powerup.usermicroservice.configuration.security.jwt.JwtProvider;
import com.pragma.powerup.usermicroservice.domain.api.IEmployeeServicePort;
import com.pragma.powerup.usermicroservice.domain.model.Employee;
import com.pragma.powerup.usermicroservice.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeHandlerImpl implements IEmployeeHandler {

    private final IEmployeeRequestMapper employeeRequestMapper;
    private final IEmployeeServicePort employeeServicePort;
    private final IRestaurantResponseMapper restaurantResponseMapper;
    private final IRestaurantClient restaurantClient;

    @Override
    public void saveEmployee(String token, EmployeeRequestDto employeeRequestDto) {
        RestaurantResponseDto response =
                restaurantClient.getRestaurant(token, employeeRequestDto.getRestaurantId());

        if(response == null)
            throw new RestaurantNotFoundException();

        String dniOwner = JwtProvider.getOwnerDni(token);
        Restaurant restaurant = restaurantResponseMapper.toEntity(response);
        Employee employee = employeeRequestMapper.toEmployee(employeeRequestDto);
        employeeServicePort.saveEmployee(dniOwner, restaurant, employee);
    }
}
