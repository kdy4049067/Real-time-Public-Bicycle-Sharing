package com.bike.public_bike.domain.member.service;

import com.bike.public_bike.domain.member.domain.Member;
import com.bike.public_bike.domain.member.dto.MemberResponseDto;
import com.bike.public_bike.domain.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberService {

    private static final int DEFAULT_POINT = 1000;

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberResponseDto findMemberByEmail(String email){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        return new MemberResponseDto(
                member.getPassword(),
                member.getNickname(),
                member.getPoint(),
                member.getEmail()
        );
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberResponseDto findByNickname(String nickname){
        Member member = memberRepository.findByEmail(nickname)
                .orElseThrow(() -> new RuntimeException("회원 없음"));

        return new MemberResponseDto(
                member.getPassword(),
                member.getNickname(),
                member.getPoint(),
                member.getEmail()
        );
    }

}
