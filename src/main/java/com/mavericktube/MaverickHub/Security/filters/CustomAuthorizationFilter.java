package com.mavericktube.MaverickHub.Security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

import static com.mavericktube.MaverickHub.Security.utils.SecurityUtils.JWT_PREFIX;
import static com.mavericktube.MaverickHub.Security.utils.SecurityUtils.PUBLIC_ENDPOINT;

@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //1.  Retrieve Request Path
        //1b. if the request path from 1a is to a public path, call the
        //      next filter in chain and terminate this filters execution.

        //2a. Retrieve access token from the request header,
        //3.  decode access token
        //4.  extract token permission
        //5/ add token permission to security context
        //    call next filter in the filter chain
        String requestPath = request.getServletPath();
        boolean isRequestPathPublic = PUBLIC_ENDPOINT.contains(requestPath);
        if(isRequestPathPublic) filterChain.doFilter(request,response);
        String authorizationRequest = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authorizationRequest.substring(JWT_PREFIX.length()).strip();
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512("Secret".getBytes()))
                .withIssuer("mavericks_hub")
                .withClaimPresence("roles")
                .build();
        DecodedJWT decodedJWT =verifier.verify(token);

       List<SimpleGrantedAuthority> authorities = decodedJWT.getClaim("roles").asList(SimpleGrantedAuthority.class);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(null, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);


    }
}
