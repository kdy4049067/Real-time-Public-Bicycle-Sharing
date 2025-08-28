package com.bike.public_bike.domain.member.service;

import com.bike.public_bike.common.exception.CommonException;
import com.bike.public_bike.domain.member.domain.Member;
import com.bike.public_bike.domain.member.dto.MemberResponseDto;
import com.bike.public_bike.domain.member.exception.MemberException;
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

    @Transactional
    public Member createMember(String password, String nickName, String email){
        Member newMember = Member.builder()
                .password(password)
                .nickname(nickName)
                .email(email)
                .point(DEFAULT_POINT)
                .build();

        return memberRepository.save(newMember);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberResponseDto findMemberById(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CommonException(MemberException.NOT_FOUND_BY_ID));

        return new MemberResponseDto(
                member.getPassword(),
                member.getNickname(),
                member.getPoint(),
                member.getEmail()
        );
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberResponseDto findMemberByEmail(String email){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() ->  new CommonException(MemberException.NOT_FOUND_BY_EMAIL));

        return new MemberResponseDto(
                member.getPassword(),
                member.getNickname(),
                member.getPoint(),
                member.getEmail()
        );
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MemberResponseDto findByNickname(String nickname){
        Member member = memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new CommonException(MemberException.NOT_FOUND_BY_NICKNAME));

        return new MemberResponseDto(
                member.getPassword(),
                member.getNickname(),
                member.getPoint(),
                member.getEmail()
        );
    }

    private void duplicateNickName(String nickName){
        if(memberRepository.existsByNickname(nickName)){
            throw new CommonException(MemberException.DUPLICATE_NICKNAME);
        }
    }

    private void duplicateEmail(String email){
        if(memberRepository.existsByEmail(email))
            throw new CommonException(MemberException.DUPLICATE_EMAIL);
    }

}
