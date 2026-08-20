package com.luv4code.ums.dto;

import lombok.Builder;

@Builder
public record UpdateUserDTO(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
