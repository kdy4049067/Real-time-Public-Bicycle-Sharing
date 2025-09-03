package com.bike.public_bike.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String passwordEncode;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false, unique = true)
    private String email;

    public void decreasePoint(int decrease){
        this.point -= decrease;
    }

}
