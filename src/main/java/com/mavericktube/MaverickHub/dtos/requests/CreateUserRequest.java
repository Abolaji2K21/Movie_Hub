package com.mavericktube.MaverickHub.dtos.requests;


import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String password;
}
