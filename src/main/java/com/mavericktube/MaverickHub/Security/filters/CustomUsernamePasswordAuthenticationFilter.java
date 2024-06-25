package com.mavericktube.MaverickHub.Security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavericktube.MaverickHub.dtos.requests.LoginRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
@AllArgsConstructor
public class CustomUsernamePasswordAuthenticationFilter
        extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        ObjectMapper mapper = new ObjectMapper();
        try {
            //1. retrieve authentication credentials for the request body
            InputStream requestBodyStream = request.getInputStream();
            //2. Convert the json data from 1 to java object (LoginRequst)
            LoginRequest  loginRequest =mapper.readValue(requestBodyStream, LoginRequest.class);


            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
                //3. create an authentication object that is to be authenticated
            Authentication authentication = new UsernamePasswordAuthenticationToken(username,password);
            //4. Pass the unauthenticated authentication object to the authenticationManager
            //4b. get back the authentication result from the authenticationManager
            Authentication authenticationResult = authenticationManager.authenticate(authentication);
            //5. put the authenticationResult in the security context
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            return authenticationResult;
        } catch  (IOException e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

//        try {
//            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
//            String token = JWT.create()
//                    .withIssuer("auth0")
//                    .sign(algorithm);
//        } catch (JWTCreationException exception){
//            // Invalid Signing configuration / Couldn't convert Claims.
//        }


    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    }
}
