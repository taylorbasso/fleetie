package com.fleetie.controller;

import com.fleetie.dto.request.LoginRequest;
import com.fleetie.dto.response.LoginResponse;
import com.fleetie.entity.User;
import com.fleetie.exception.ResourceNotFoundException;
import com.fleetie.service.JwtService;
import com.fleetie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userService.getByUsername(request.getUsername())
            .orElseThrow(() -> new ResourceNotFoundException("User not found")); //this should be a 401
        String jwt = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}

