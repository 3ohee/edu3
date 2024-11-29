package com.ict.edu3.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CORS 설정 적용
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // CSRF 비활성화 (REST API에서는 필요 없음)
                .csrf(csrf -> csrf.disable())
                // 요청별 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                                // 특정 URL에 인증없이 허용
                                .requestMatchers("/auth/**").permitAll()
                                
                                // 나머지는 인증 필요
                                .anyRequest().authenticated());
    
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        // 허용할 origin 설정
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        // 
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        corsConfig.setAllowCredentials(true);


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return source;

    } 
}
