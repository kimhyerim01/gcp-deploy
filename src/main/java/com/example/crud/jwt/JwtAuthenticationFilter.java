//package com.example.crud.jwt;
//
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Collections;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        // 1. 쿠키에서 token 찾기
//        String jwt = extractTokenFromCookies(request);
//
//        // 2. JWT가 있고, 유효하다면
//        if (jwt != null && jwtUtil.validateToken(jwt)) {
//            String email = jwtUtil.extractEmail(jwt); // 이메일 꺼내기
//
//            // 3. 인증 객체 생성
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                    email,
//                    null,
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")) // 기본 권한
//            );
//
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//            // 4. SecurityContext에 등록 (인증 완료!)
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//
//        // 5. 다음 필터로 넘기기
//        filterChain.doFilter(request, response);
//    }
//
//    private String extractTokenFromCookies(HttpServletRequest request) {
//        if (request.getCookies() == null) return null;
//
//        for (Cookie cookie : request.getCookies()) {
//            if ("token".equals(cookie.getName())) {
//                return cookie.getValue();
//            }
//        }
//
//        return null;
//    }
//}
//
