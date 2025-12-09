package com.example.demo.config;

import com.example.demo.dto.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.example.demo.security.UserPrincipal;
import com.example.demo.dto.User;

import java.io.IOException;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {


        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

       User user = new User(
         principal.getName(),
         principal.isEnabled()
      );



        // 성공 응답 반환
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json;charset=UTF-8");

        String json = String.format(
                "{ \"success\": true,  \"name\": \"%s\" }",
                user.getName()
        );

        response.getWriter().write(json);
       response.sendRedirect("http://localhost:3001/login/success");
	
	    }
}

