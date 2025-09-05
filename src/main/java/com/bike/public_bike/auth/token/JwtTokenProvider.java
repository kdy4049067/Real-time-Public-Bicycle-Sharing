package com.bike.public_bike.auth.token;

import com.bike.public_bike.auth.domain.TokenProvider;
import com.bike.public_bike.auth.exception.AuthErrorCode;
import com.bike.public_bike.common.exception.AuthException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final Key key;
    private final long accessExpirationMillis;
    private final long refreshExpirationMillis;

    public JwtTokenProvider(
        @Value("${jwt.secret}") String key,
        @Value("${jwt.access.expiration}") long accessExpirationMillis,
        @Value("${jwt.refresh.expiration}") long refreshExpirationMillis
    ){
        this.key = Keys.hmacShaKeyFor(key.getBytes());
        this.accessExpirationMillis = accessExpirationMillis;
        this.refreshExpirationMillis = refreshExpirationMillis;
    }

    @Override
    public String createAccessToken(Long memberId){
        return create(memberId, accessExpirationMillis);
    }

    @Override
    public String createRefreshToken(Long memberId){
        return create(memberId, refreshExpirationMillis);
    }

    @Override
    public Long extractMemberId(String token){
        Claims claims = parseToken(token);
        return Long.valueOf(claims.getSubject());
    }

    @Override
    public boolean isAccessTokenExpired(String token){
        try{
        Claims claims = parseToken(token);

        return claims.getExpiration()
                .before(new Date());
        }
        catch(ExpiredJwtException e) {
            return true;
        }
    }

    @Override
    public Authentication getAuthentication(Long memberId){
        List<SimpleGrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_USER"));

        User principal = new User(
                String.valueOf(memberId),
                "",
                authorities
        );

        return new UsernamePasswordAuthenticationToken(
                principal,
                null,
                authorities
        );
    }

    private String create(Long memberId, long expirationMillis){
        Date now = new Date();
        Date expire = new Date(now.getTime() + expirationMillis);

        return Jwts.builder()
                .setSubject(String.valueOf(memberId))
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims parseToken(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch(ExpiredJwtException e){
            throw new AuthException(AuthErrorCode.TOKEN_EXPIRED);
        }
        catch(SignatureException e){
            throw new AuthException(AuthErrorCode.TOKEN_INVALID_SIGNATURE);
        }
        catch(JwtException | IllegalArgumentException e){
            throw new AuthException(AuthErrorCode.TOKEN_NULL_OR_INVALID);
        }

    }

}
