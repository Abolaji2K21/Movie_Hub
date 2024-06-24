package com.mavericktube.MaverickHub.Services;


import com.mavericktube.MaverickHub.Models.User;
import com.mavericktube.MaverickHub.dtos.requests.CreateUserRequest;
import com.mavericktube.MaverickHub.dtos.responds.CreateUserResponse;
import com.mavericktube.MaverickHub.exceptions.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTest {



    @Autowired
    private UserService userService;

    @Test
    public void registerTest(){
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("test@email.com");
        request.setPassword("password");
        CreateUserResponse response = userService.register(request);
        assertNotNull (response);
        assertTrue(response.getMessage().contains("successfully"));


    }


    @Test
    @DisplayName("test that user can be retrieved by id ")
    @Sql(scripts = {"/db/data.sql"})
    public  void testGetUserById() throws UserNotFoundException {
        User user = userService.getById(200L);
        assertThat(user).isNotNull();
    }
}
