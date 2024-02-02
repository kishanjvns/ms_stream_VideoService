package com.tech.kj.conf;

import com.tech.kj.CommonConstant;
import com.tech.kj.JwtFilter;
import com.tech.kj.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private static final String[] WHITE_LIST_URL = {"/stream/api/v1/auth/**"};
    private final JwtFilter jwtAuthFilter;
    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    public SecurityFilterChain configFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(auth->
                    auth.requestMatchers(CommonConstant.WHITE_LIST_URL)
                    .permitAll()
                    .requestMatchers("/stream/api/v1/videos/**").hasAnyRole("USER","ADMIN")
                    .anyRequest().authenticated()
                    )
                .csrf(csrf ->csrf.disable())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .build();
    }
}
