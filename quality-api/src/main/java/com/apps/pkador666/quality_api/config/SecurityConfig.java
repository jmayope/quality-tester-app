package com.apps.pkador666.quality_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth -> auth
              // .requestMatchers("/auth/**").permitAll()  // ← CON /api
              // .anyRequest().authenticated()
              .anyRequest().permitAll()
          )
          .httpBasic(basic -> basic.disable())
          .formLogin(form -> form.disable());

      return http.build();
  }
  // @Bean
  // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  //   http
  //     .csrf(csrf -> csrf.disable()) // ← IMPORTANTE: Deshabilitar CSRF
  //     .sessionManagement(session -> session
  //       .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // APIs REST sin sesión
  //     )
  //     .authorizeHttpRequests(auth -> auth
  //       .requestMatchers("/auth/**").permitAll() // Público
  //       .requestMatchers("/h2-console/**").permitAll() // Si usas H2
  //       .anyRequest().authenticated())
  //     .headers(headers -> headers
  //       .frameOptions(frameOptions -> frameOptions.sameOrigin()) // Para H2 console
  //     )
  //     .httpBasic(basic -> basic.disable()) // Deshabilitar Basic Auth
  //     .formLogin(form -> form.disable()); // Deshabilitar form login

  //   return http.build();
  // }
}
