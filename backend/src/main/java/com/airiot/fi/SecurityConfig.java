package com.airiot.fi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/login", "/api/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(formLogin -> formLogin
            .loginProcessingUrl("/login")
            .successHandler((request, response, authentication) -> {
              response.setStatus(200);
            })
            .failureHandler((request, response, exception) -> {
              response.setStatus(401);
            })
            .permitAll()
        )
        .logout(logout -> logout.permitAll());
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails root = User.builder()
        .username("root")
        .password(passwordEncoder().encode("root"))
        .roles("ADMIN", "USER", "GUEST")
        .build();
    UserDetails user = User.builder()
        .username("user")
        .password(passwordEncoder().encode("user"))
        .roles("USER", "GUEST")
        .build();
    UserDetails guest = User.builder()
        .username("guest")
        .password(passwordEncoder().encode("guest"))
        .roles("GUEST")
        .build();
    return new InMemoryUserDetailsManager(root, user, guest);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
