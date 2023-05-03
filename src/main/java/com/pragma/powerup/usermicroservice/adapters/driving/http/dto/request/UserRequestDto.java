package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserRequestDto {
    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 13, min = 10)
    private String phone;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^\\d+$", message = "Only accepts numbers")
    private String dniNumber;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private Long idRole;
}
