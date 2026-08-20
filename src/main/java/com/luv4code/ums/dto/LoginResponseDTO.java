package com.luv4code.ums.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record LoginResponseDTO(
        String token,
        String username,
        Set<String> roles
) {
}
