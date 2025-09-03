package com.bike.public_bike.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignupRequest (
        @NotBlank
        String password,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String nickname
){}
