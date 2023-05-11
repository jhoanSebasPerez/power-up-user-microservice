package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class UserRequestDto {
    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    @Email(message = "Not a valid email")
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 13)
    @Pattern(regexp = "^\\+[0-9]+$", message = "Include country code")
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
}
