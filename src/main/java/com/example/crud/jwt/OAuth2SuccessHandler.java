package com.example.crud.jwt;

import com.example.crud.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // 로그인 성공한 사용자 정보 가져오기
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = (String) oAuth2User.getAttributes().get("email");

        // JWT 생성
        String token = jwtUtil.generateToken(email);

        // JWT를 HttpOnly 쿠키로 설정
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);        // 자바스크립트 접근 차단
        cookie.setSecure(true);          // HTTPS에서만 전송 (개발 중이면 false로 해도 됨)
        cookie.setPath("/");             // 전체 경로에서 접근 가능
        cookie.setMaxAge(60 * 60);       // 1시간 유효

        response.addCookie(cookie);

        // 로그인 성공 후 리다이렉션 (예: 홈 화면)
        response.sendRedirect("/");
    }
}
