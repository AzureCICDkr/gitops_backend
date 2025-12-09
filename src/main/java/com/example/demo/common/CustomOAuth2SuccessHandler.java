package com.example.demo.common;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomOAuth2SuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        HttpSession session = request.getSession(true);

        // principal을 세션에 저장 → Redis로 자동 저장됨
        session.setAttribute("user", authentication.getPrincipal());

        // 로그인 성공 시 프론트엔드로 리다이렉트
        response.sendRedirect("http://localhost:3001/login/success");
    }
}

