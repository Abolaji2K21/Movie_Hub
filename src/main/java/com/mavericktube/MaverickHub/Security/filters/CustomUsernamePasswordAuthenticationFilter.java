package com.mavericktube.MaverickHub.Security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavericktube.MaverickHub.dtos.requests.LoginRequest;
import com.mavericktube.MaverickHub.dtos.responds.BaseResponse;
import com.mavericktube.MaverickHub.dtos.responds.LoginResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.*;

@AllArgsConstructor
public class CustomUsernamePasswordAuthenticationFilter
        extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

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

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Successful authentication");
        loginResponse.setToken(generateAccessToken(authResult));



        BaseResponse<LoginResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(loginResponse);
        baseResponse.setStatus(true);
        baseResponse.setCode(HttpStatus.OK.value());
               response.getOutputStream().write(mapper.writeValueAsBytes(baseResponse));
               response.flushBuffer();
               chain.doFilter(request,response);

//        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
        }

    private static String generateAccessToken(Authentication authResult) {
        return JWT.create()
             .withIssuer("mavericks_hub")
             .withArrayClaim("roles",getClaimsFrom(authResult.getAuthorities()))
             .withExpiresAt(Instant.now().plusSeconds(24 * 60 * 60))
             .sign(Algorithm.HMAC512("secret"));
    }

    private static String[] getClaimsFrom(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map((grantedAuthority) -> grantedAuthority.getAuthority())
                .toArray(String[]::new);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage(exception.getMessage());


        BaseResponse<LoginResponse> baseResponse = new BaseResponse<>();
        baseResponse.setData(loginResponse);
        baseResponse.setStatus(false);
        baseResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        response.getOutputStream().write(mapper.writeValueAsBytes(baseResponse));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        response.sendError(HttpStatus.UNAUTHORIZED.value());
        response.flushBuffer();
    }
}
