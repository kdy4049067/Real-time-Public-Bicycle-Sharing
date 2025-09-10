package com.bike.public_bike.auth.service;

import com.bike.public_bike.auth.exception.AuthErrorCode;
import com.bike.public_bike.common.exception.AuthException;
import com.bike.public_bike.domain.member.domain.Member;
import com.bike.public_bike.domain.member.dto.MemberResponseDto;
import com.bike.public_bike.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto login(String email, String password){
        MemberResponseDto member = memberService.findMemberByEmail(email);

        if(!passwordEncoder.matches(password, member.passwordEncode())){
            throw new AuthException(AuthErrorCode.INVALID_PASSWORD);
        }

        return member;
    }

    public Member signup(String email, String password, String nickname){
        String passwordEncode = passwordEncoder.encode(password);

        return memberService.createMember(email, passwordEncode, nickname);
    }

}
