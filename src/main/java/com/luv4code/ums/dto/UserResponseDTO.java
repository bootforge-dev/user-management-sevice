package com.luv4code.ums.dto;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName
) {
}
