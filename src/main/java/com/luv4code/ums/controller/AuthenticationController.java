package com.luv4code.ums.controller;

import com.luv4code.ums.dto.CreateUserDTO;
import com.luv4code.ums.dto.LoginRequestDTO;
import com.luv4code.ums.dto.LoginResponseDTO;
import com.luv4code.ums.dto.UserResponseDTO;
import com.luv4code.ums.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/create_normal_user")
    public ResponseEntity<UserResponseDTO> createNormalUser(@RequestBody @Valid CreateUserDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationService.registerNormalUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(authenticationService.login(request));
    }


}
