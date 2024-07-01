package com.mavericktube.MaverickHub.Security.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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
        request.getHeader("AUTHORIZATION");






    }
}
