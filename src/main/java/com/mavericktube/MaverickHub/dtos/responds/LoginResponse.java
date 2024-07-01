package com.mavericktube.MaverickHub.dtos.responds;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String message;
    private String token;
}
