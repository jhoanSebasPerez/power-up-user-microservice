package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dniNumber;
}
