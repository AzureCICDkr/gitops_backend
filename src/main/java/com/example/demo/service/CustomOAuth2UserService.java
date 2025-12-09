package com.example.demo.service;

import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.dto.User;
import com.example.mapper.UserDao;
import com.example.demo.security.UserPrincipal;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserDao userDao;

    public CustomOAuth2UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // Provider(origin)
        String provider = userRequest.getClientRegistration().getRegistrationId(); // google

        // Google Attribute 값 꺼내기
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        // DB 조회
        User user = userDao.findByEmail(email);

        if (user == null) {
            // 신규 사용자 → INSERT
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setProvider(provider);
            newUser.setRole("ROLE_USER");
            newUser.setEnabled(true);

            userDao.insertUser(newUser);
            user = newUser;

        } else {
            // 기존 사용자 정보 업데이트
            user.setName(name);
            user.setProvider(provider);

            userDao.updateUser(user);
        }

        // SecurityContext 안에 저장될 UserPrincipal 생성
        return new UserPrincipal(user, attributes);
    }
}

