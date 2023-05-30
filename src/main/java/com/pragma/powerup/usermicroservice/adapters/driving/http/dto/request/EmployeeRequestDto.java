package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequestDto {

    private String firstName;

    private String lastName;

    private String dniNumber;

    private String phone;

    private String email;

    private String password;

    private Long restaurantId;

    private LocalDate birthDate;
}
