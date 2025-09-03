package com.bike.public_bike.auth.dto;

public record LoginResponse (
    Long id,
    String email,
    String nickname
){}
