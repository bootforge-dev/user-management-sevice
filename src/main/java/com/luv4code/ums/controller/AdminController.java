package com.luv4code.ums.controller;

import com.luv4code.ums.dto.CreateUserDTO;
import com.luv4code.ums.dto.UserResponseDTO;
import com.luv4code.ums.service.AuthenticationService;
import com.luv4code.ums.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/create_admin_user")
    public ResponseEntity<UserResponseDTO> createAdminUser(@RequestBody @Valid CreateUserDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationService.registerAdminUser(request));
    }


}
