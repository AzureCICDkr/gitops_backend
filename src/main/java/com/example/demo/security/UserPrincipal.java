package com.example.demo.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.demo.dto.User;

public class UserPrincipal implements OAuth2User, UserDetails {

    private final User user;
    private final Map<String, Object> attributes;

    public UserPrincipal(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    // Google OAuth2 User 정보 (email, name 등)
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    // Security에서 식별자로 사용하는 name (보통 email)
    @Override
    public String getName() {
        return user.getEmail();
    }

    // --- Spring Security 권한 처리 ---
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(
                new SimpleGrantedAuthority(user.getRole())
        );
    }

    // 비밀번호 사용 안 함(OAuth2 로그인)
    @Override
    public String getPassword() {
        return null;
    }

    // username = email
    @Override
    public String getUsername() {
        return user.getEmail();
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    // DB의 User 엔티티 접근하고 싶을 때
    public User getUser() {
        return user;
    }
}

