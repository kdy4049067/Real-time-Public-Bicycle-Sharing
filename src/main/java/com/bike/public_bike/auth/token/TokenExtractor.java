package com.bike.public_bike.auth.token;

import com.bike.public_bike.auth.exception.AuthErrorCode;
import com.bike.public_bike.common.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class TokenExtractor {

    private static final String AUTH_HEADER = "Authorization";
    private static final String COOKIE_PREFIX = "Bearer ";

    public String extractToken(HttpServletRequest request){
        String header = request.getHeader(AUTH_HEADER);

        if(header == null || !header.startsWith(COOKIE_PREFIX)){
            throw new AuthException(AuthErrorCode.TOKEN_NULL_OR_INVALID);
        }

        return header.substring(COOKIE_PREFIX.length());
    }

}
