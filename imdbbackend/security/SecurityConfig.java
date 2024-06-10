package com.imdbv1.imdbbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        configuration.setAllowCredentials(true); // Allow credentials
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler, CustomOAuth2UserService customOAuth2UserService) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .exceptionHandling(customizer -> customizer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(requests -> requests
    .requestMatchers("/", "/login**", "/error", "/oauth2/**", "/api/v1/user/register", "/api/v1/user/login","/api/v1/user/logout", "/api/v1/movie/**", "/api/v1/search/**", "/api/v1/actor/**","/api/v1/watchlist/**").permitAll()
    .anyRequest().authenticated())

            .oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/login")
                .successHandler(oAuth2LoginSuccessHandler)
                .defaultSuccessUrl("http://localhost:3000/home", true)
                .failureUrl("http://localhost:3000/loginFailure")
                .userInfoEndpoint().userService(customOAuth2UserService)); // Ensure this line is present
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
