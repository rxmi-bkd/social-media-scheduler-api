package org.bkd.social_media_scheduler_api.authentication.frameworks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private final AuthenticationFilter authenticationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity

        .csrf(AbstractHttpConfigurer::disable)

        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .authorizeHttpRequests(auth -> auth.requestMatchers("/public/**")
                                           .permitAll()
                                           .requestMatchers("/admin/**")
                                           .hasRole("ADMIN")
                                           .anyRequest()
                                           .authenticated())

        .addFilterBefore(authenticationFilter, LogoutFilter.class);

    return httpSecurity.build();
  }
}