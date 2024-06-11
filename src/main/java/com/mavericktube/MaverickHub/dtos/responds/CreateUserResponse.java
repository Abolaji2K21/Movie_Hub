package com.mavericktube.MaverickHub.dtos.responds;


import lombok.Data;

@Data
public class CreateUserResponse {
    private Long id;
    private String email;
    private String message;
}
