package com.bike.public_bike.domain.member.repository;

import com.bike.public_bike.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByEmail(String email);

}
