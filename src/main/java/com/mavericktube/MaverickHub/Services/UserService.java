package com.mavericktube.MaverickHub.Services;

import com.mavericktube.MaverickHub.Models.User;
import com.mavericktube.MaverickHub.dtos.requests.CreateUserRequest;
import com.mavericktube.MaverickHub.dtos.responds.CreateUserResponse;

import java.util.Optional;

public interface UserService {
    CreateUserResponse register(CreateUserRequest request);


    User getById(Long userId);
}
