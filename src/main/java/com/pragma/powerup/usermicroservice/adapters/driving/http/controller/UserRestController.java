package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.EmployeeRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IEmployeeHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
//@SecurityRequirement(name = "jwt")
public class UserRestController {

    private final IUserHandler personHandler;
    private final IEmployeeHandler employeeHandler;

    @Operation(summary = "Add a new owner",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Owner created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Owner already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/create-owner")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, String>> saveOwner(@Validated @RequestBody UserRequestDto userRequestDto) {
        personHandler.saveOwner(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.OWNER_CREATED_MESSAGE));
    }

    @Operation(summary = "Add a new employee",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Owner created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Owner already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/create-employee")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<Map<String, String>> saveEmployee(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @Validated @RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeHandler.saveEmployee(token, employeeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.EMPLOYEE_CREATED_MESSAGE));
    }


    @Operation(summary = "Verify if the user is an owner",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Return response field",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/{userId}/is-owner")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, Boolean>> isOwner(@PathVariable("userId") String userDni) {
        boolean isOwner = personHandler.isOwner(userDni);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("response", isOwner));
    }
}
