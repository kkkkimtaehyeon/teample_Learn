package com.example.teample_learn.config.auth;

import com.example.teample_learn.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(request -> request
                .requestMatchers("/**", "/css/**", "/images/**", "/js/**").permitAll()
                //.requestMatchers("/post/**","/post").hasRole(Role.USER.name())
                .anyRequest().authenticated()
        );

        http.logout(logout -> logout.logoutSuccessUrl("/"));

        http.oauth2Login(oauth ->
                oauth.userInfoEndpoint(endPoint ->
                        endPoint.userService(customOAuth2UserService)));

        return http.build();
    }
}
