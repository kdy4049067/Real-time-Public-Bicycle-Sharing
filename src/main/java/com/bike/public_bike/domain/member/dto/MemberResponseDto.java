package com.bike.public_bike.domain.member.dto;

public record MemberResponseDto(
        Long id,
        String passwordEncode,
        String nickname,
        Integer point,
        String email
) {}
