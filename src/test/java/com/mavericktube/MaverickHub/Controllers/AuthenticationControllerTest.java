package com.mavericktube.MaverickHub.Controllers;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavericktube.MaverickHub.dtos.requests.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Test
    void authenticationUserTest() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("jor@email.com");
        request.setPassword("password");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().isOk())
                .andDo(print());
}

    @Test
    void testThatAuthenticationFailsForIncorrectCredentials() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("jor02@email.com");
        request.setPassword("password");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().isUnauthorized())
                .andDo(print());

    }


}