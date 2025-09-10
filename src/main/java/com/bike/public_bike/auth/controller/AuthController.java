package com.bike.public_bike.auth.controller;

import com.bike.public_bike.auth.dto.LoginRequest;
import com.bike.public_bike.auth.dto.LoginResponse;
import com.bike.public_bike.auth.dto.SignupRequest;
import com.bike.public_bike.auth.dto.SignupResponse;
import com.bike.public_bike.auth.service.AuthService;
import com.bike.public_bike.domain.member.domain.Member;
import com.bike.public_bike.domain.member.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest){
        MemberResponseDto memberResponseDto = authService.login(loginRequest.email(), loginRequest.password());
        LoginResponse loginResponse = new LoginResponse(memberResponseDto.id(), memberResponseDto.email(), memberResponseDto.nickname());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<SignupResponse> signup(SignupRequest signupRequest){
        Member member = authService.signup(signupRequest.email(), signupRequest.password(), signupRequest.nickname());
        SignupResponse signupResponse = new SignupResponse(member.getEmail(), member.getPasswordEncode());

        return ResponseEntity.ok(signupResponse);
    }

}
