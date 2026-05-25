package com.jobhuntinger.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
        http.sessionManagement(sessionConfig -> sessionConfig
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.csrf(csrfConfig -> csrfConfig
                .ignoringRequestMatchers("/h2-console/**"));

        http.headers(headersConfig -> headersConfig
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http.authorizeHttpRequests(requestConcifg -> requestConcifg
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated());

        http.oauth2ResourceServer(rsConfig -> rsConfig
                .jwt(Customizer.withDefaults()));

        return http.build();
    }
}
