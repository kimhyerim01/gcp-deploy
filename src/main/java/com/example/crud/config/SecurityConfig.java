//package com.example.crud.config;
//
//import com.example.crud.jwt.OAuth2SuccessHandler;
//import com.example.crud.jwt.JwtAuthenticationFilter;
//import com.example.crud.oauth.CustomOAuth2UserService;
//import com.example.crud.repository.HttpCookieOAuth2AuthorizationRequestRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final OAuth2SuccessHandler oAuth2SuccessHandler;
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(oauth2 -> oauth2
//                        .authorizationEndpoint(auth ->
//                                auth.authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository)) // ✅ () 호출 필수
//
//                        .userInfoEndpoint(userInfo ->
//                                userInfo.userService(customOAuth2UserService)
//                        )
//                        .successHandler(oAuth2SuccessHandler)
//
//                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//}
