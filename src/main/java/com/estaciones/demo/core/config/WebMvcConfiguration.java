package com.estaciones.demo.core.config;

import com.estaciones.demo.core.Jwt.JwtAuthenticationFilter;
import com.estaciones.demo.core.interceptor.TenantInterceptor;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity()
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final TenantInterceptor tenantInterceptor;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authProvider;

  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(tenantInterceptor).excludePathPatterns("/auth/login");
  }

  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
            .allowedOrigins("*")
            .allowedHeaders("*");
  }

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
                        .exceptionHandling().authenticationEntryPoint((request, response, ex)
                                -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage() ));
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            })
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }
}