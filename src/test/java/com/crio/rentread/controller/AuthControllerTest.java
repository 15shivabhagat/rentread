package com.crio.rentread.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.crio.rentread.exchange.request.RegisterUserRequest;
import com.crio.rentread.exchange.response.Response;
import com.crio.rentread.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisteredUser_SuccessResponse() throws JsonProcessingException, Exception {
        RegisterUserRequest request = RegisterUserRequest.builder()
                                                        .firstName("Test")
                                                        .lastName("User")
                                                        .email("test@gmail.com")
                                                        .password("password")
                                                        .build();
    
        Response response = new Response("User Registered Successfully!", HttpStatus.CREATED);
        Mockito.when(authService.registerUser(request)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isCreated());
            // .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());
    }
}
