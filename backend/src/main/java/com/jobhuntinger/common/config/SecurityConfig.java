package com.jobhuntinger.common.config;

import com.jobhuntinger.common.constants.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${client.url}")
    private String clientUrl;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {
        http.sessionManagement(sessionConfig -> sessionConfig
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.cors(Customizer.withDefaults());

        http.csrf(csrfConfig -> csrfConfig
                .ignoringRequestMatchers(Constants.H2_PATTERN));

        http.headers(headersConfig -> headersConfig
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http.authorizeHttpRequests(requestConcifg -> requestConcifg
                .requestMatchers(Constants.H2_PATTERN, Constants.ACTUATOR_PATTERN).permitAll()
                .anyRequest().authenticated());

        http.oauth2ResourceServer(rsConfig -> rsConfig
                .jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(clientUrl));
        config.setAllowedMethods(Constants.ALLOWED_METHODS);
        config.setAllowedHeaders(List.of(Constants.WILDCARD));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(Constants.REST_PATTERN, config);
        return source;
    }
}
