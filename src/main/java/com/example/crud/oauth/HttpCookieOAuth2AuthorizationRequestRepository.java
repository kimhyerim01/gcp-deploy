//package com.example.crud.oauth;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.SerializationUtils;
//
//import java.util.Base64;
//import java.util.Optional;
//
//@Slf4j
//@Repository
//@RequiredArgsConstructor
//public class HttpCookieOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository {
//
//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    // 쿠키 조회
//    public static Optional<Cookie> getCookie(HttpServletRequest request, String name) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null) return Optional.empty();
//
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals(name)) {
//                return Optional.of(cookie);
//            }
//        }
//
//        return Optional.empty();
//    }
//
//    // 쿠키 추가
//    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
//        Cookie cookie = new Cookie(name, value);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        cookie.setMaxAge(maxAge);
//        response.addCookie(cookie);
//    }
//
//    // 쿠키 삭제
//    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null) return;
//
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals(name)) {
//                cookie.setValue("");
//                cookie.setPath("/");
//                cookie.setMaxAge(0);
//                response.addCookie(cookie);
//            }
//        }
//    }
//
//    // 직렬화 - Base64 인코딩된 JSON 문자열
//    public static String serialize(Object object) {
//        try {
//            byte[] bytes = objectMapper.writeValueAsBytes(object);
//            return Base64.getUrlEncoder().encodeToString(bytes);
//        } catch (Exception ex) {
//            log.error("쿠키 직렬화 실패", ex);
//            throw new IllegalArgumentException("쿠키 직렬화 실패", ex);
//        }
//    }
//
//    // 역직렬화 - JSON 문자열 → 객체로
//    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
//        try {
//            byte[] decoded = Base64.getUrlDecoder().decode(cookie.getValue());
//            return objectMapper.readValue(decoded, cls);
//        } catch (Exception ex) {
//            log.error("쿠키 역직렬화 실패", ex);
//            throw new IllegalArgumentException("쿠키 역직렬화 실패", ex);
//        }
//    }
//}