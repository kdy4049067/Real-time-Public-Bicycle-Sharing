package com.bike.public_bike.auth.filter;

import com.bike.public_bike.auth.token.JwtTokenProvider;
import com.bike.public_bike.auth.token.TokenExtractor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenExtractor tokenExtractor;
    private final JwtTokenProvider tokenProvider;
    private final String TOKEN_ATTRIBUTE = "memberId";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException{
        String token = tokenExtractor.extractToken(request);
        Long memberId = tokenProvider.extractMemberId(token);

        Authentication authentication = tokenProvider.getAuthentication(memberId);
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        request.setAttribute(TOKEN_ATTRIBUTE, memberId);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        String uri = request.getRequestURI();

        return uri.startsWith("/login");
    }

}
