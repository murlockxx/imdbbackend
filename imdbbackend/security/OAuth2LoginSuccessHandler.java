package com.imdbv1.imdbbackend.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Extract user information
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Create user info object
        UserInfo userInfo = new UserInfo(email, name);

        System.out.println(userInfo.email);
        System.out.println(userInfo.name);


        // Set response type to JSON
        response.setContentType("application/json;charset=UTF-8");

        // Write user info as JSON
        response.getWriter().write(objectMapper.writeValueAsString(userInfo));
        response.flushBuffer();
    }

    private static class UserInfo {
        public String email;
        public String name;

        public UserInfo(String email, String name) {
            this.email = email;
            this.name = name;
        }
    }
}
