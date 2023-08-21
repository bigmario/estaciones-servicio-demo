package com.estaciones.demo.core.config;

import com.estaciones.demo.core.Jwt.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http.cors().and().csrf().disable()
            .authorizeHttpRequests(authRequest -> {
                        authRequest
                                .requestMatchers("/auth/login").permitAll()
                                .requestMatchers("/auth/register").hasAuthority("ADMIN")
                                .anyRequest().authenticated();
                    }
                )
            .sessionManagement(sessionManager->
            {
                try {
                    sessionManager
                      .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                            .exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
                                response.sendError(
                                        HttpServletResponse.SC_UNAUTHORIZED,
                                        ex.getMessage()
                                );
                            });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}
