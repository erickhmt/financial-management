package com.financeapp.api.domain.user;

import jakarta.validation.constraints.NotEmpty;

public record AuthenticationRequestDTO(
    @NotEmpty String username,
    @NotEmpty String password) {
}
