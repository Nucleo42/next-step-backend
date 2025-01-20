package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.service.user.UserService;
import com.example.demo.domain.user.User;


import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class CustomBasicAuthFilter extends OncePerRequestFilter {

    private final UserService userService;

    public CustomBasicAuthFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String headerAuthorization = request.getHeader("Authorization");
        if (headerAuthorization == null || !headerAuthorization.startsWith("Basic ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String basicToken = headerAuthorization.substring(6); // "Basic " length is 6
        byte[] basicTokenDecoded = Base64.getDecoder().decode(basicToken);
        String basicTokenValue = new String(basicTokenDecoded);
        String[] credentials = basicTokenValue.split(":", 2);

        if (credentials.length == 2) {
            String email = credentials[0];
            String password = credentials[1];

            Optional<User> authenticatedUser = userService.authenticateUser(email, password);
            if (authenticatedUser.isPresent()) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        authenticatedUser.get(), null, null);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}