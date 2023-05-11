package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.configuration.ControllerAdvisor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.core.Is.is;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @InjectMocks
    UserRestController userRestController;

    @Mock
    IUserHandler userHandler;

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    UserRequestDto userRequestDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController, ControllerAdvisor.class).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        userRequestDto = UserRequestDto.builder()
                .firstName("john")
                .lastName("doe")
                .birthDate(LocalDate.of(1995, 5, 23))
                .phone("+573423453459")
                .email("john@email.com")
                .password("4453453")
                .dniNumber("1234567890")
                .build();
    }

    @Test
    void createOwnerTest() throws Exception {
        MockHttpServletRequestBuilder request = post("/user/create-owner")
                .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userRequestDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath(String.format("$.%s", Constants.RESPONSE_MESSAGE_KEY))
                        .value(Constants.OWNER_CREATED_MESSAGE));

        verify(userHandler).saveOwner(any(UserRequestDto.class));
    }

    @Test
    void createOwnerWithWrongDniTest() throws Exception {
        userRequestDto.setDniNumber("34234534a");

        MockHttpServletRequestBuilder request = post("/user/create-owner")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].dniNumber").value("Only accepts numbers"))
                .andReturn();
    }

    @Test
    void createOwnerWithWrongEmail() throws Exception {
        userRequestDto.setEmail("johmdom.com");

        MockHttpServletRequestBuilder request = post("/user/create-owner")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].email").value("Not a valid email"))
                .andReturn();
    }

    @Test
    void createOwnerWithPhoneMoreThan13Numbers() throws Exception {
        userRequestDto.setPhone("+57394729837489273");

        MockHttpServletRequestBuilder request = post("/user/create-owner")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDto));

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].phone").value("size must be between 10 and 13"))
                .andReturn();
    }
}