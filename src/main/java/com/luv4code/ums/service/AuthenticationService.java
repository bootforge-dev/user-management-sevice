package com.luv4code.ums.service;

import com.luv4code.ums.dto.CreateUserDTO;
import com.luv4code.ums.dto.LoginRequestDTO;
import com.luv4code.ums.dto.LoginResponseDTO;
import com.luv4code.ums.dto.UserResponseDTO;
import com.luv4code.ums.entity.User;
import com.luv4code.ums.jwt.JwtService;
import com.luv4code.ums.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserResponseDTO registerNormalUser(CreateUserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.username())) {
            throw new RuntimeException("User already exists!");
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        User user = User.builder()
                .username(userDTO.username())
                .email(userDTO.email())
                .password(passwordEncoder.encode(userDTO.password()))
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .roles(roles)
                .build();
        User createdUser = userRepository.save(user);
        return toDto(createdUser);
    }

    public UserResponseDTO registerAdminUser(CreateUserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.username())) {
            throw new RuntimeException("User already exists!");
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");

        User user = User.builder()
                .username(userDTO.username())
                .email(userDTO.email())
                .password(passwordEncoder.encode(userDTO.password()))
                .firstName(userDTO.firstName())
                .lastName(userDTO.lastName())
                .roles(roles)
                .build();
        User createdUser = userRepository.save(user);
        return toDto(createdUser);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
        ));
        User user = userRepository.findByUsername(request.username()).orElseThrow(
                () -> new UsernameNotFoundException("User not found!")
        );
        String token = jwtService.generateToken(user);

        return LoginResponseDTO.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

    private UserResponseDTO toDto(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

}
