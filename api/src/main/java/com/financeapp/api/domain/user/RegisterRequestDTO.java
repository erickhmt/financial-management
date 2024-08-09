package com.financeapp.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record RegisterRequestDTO(
        @NotEmpty String username,
        @NotEmpty String name,
        @NotEmpty @Email String email,
        @NotEmpty String password) {
}
