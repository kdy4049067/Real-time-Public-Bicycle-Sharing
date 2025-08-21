package com.bike.public_bike.domain.member.dto;

public record MemberResponseDto(
        String password,
        String nickname,
        Integer point,
        String email
) {}
