package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.CustomOAuth2UserService;
import com.example.demo.config.CustomAuthSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private CustomAuthSuccessHandler customAuthSuccessHandler;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )

            // --- OAuth2 Login ---
            .oauth2Login(oauth -> oauth
                .successHandler(customAuthSuccessHandler) 
                .authorizationEndpoint(auth -> auth
		.authorizationRequestRepository(authorizationRequestRepository()))
		.userInfoEndpoint(userInfo ->
                    userInfo.userService(customOAuth2UserService)
                )
            )

            // --- Logout (POST 로그아웃) ---
            .logout(logout -> logout
                .logoutUrl("/logout")                    // POST /logout
                .logoutSuccessUrl("/login")              // 로그아웃 후 이동
                .invalidateHttpSession(true)             // 세션 무효화
                .deleteCookies("JSESSIONID")             // Redis 세션 쿠키 삭제
            );

        return http.build();
    }




@Bean
public HttpSessionOAuth2AuthorizationRequestRepository authorizationRequestRepository() {
    return new HttpSessionOAuth2AuthorizationRequestRepository();
}







}

