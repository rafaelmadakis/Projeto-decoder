package com.ead.authuser.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {


  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private AuthenticationEntryPointImpl authenticationEntryPoint;

  @Autowired
  private AuthenticationConfiguration authenticationConfiguration;

  private static final String[] AUTH_WHITELIST = {
      "/auth/**"
  };

  @Bean
  public AuthenticationJwtFilter authenticationJwtFilter() {
    return new AuthenticationJwtFilter();
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests()
        .requestMatchers(AUTH_WHITELIST).permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().disable();
//        .formLogin();
    http.addFilterBefore(authenticationJwtFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }



  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

//  @Bean
//  public UserDetailsService userDetailsService() {
//    UserDetails user = User.withUsername("admin")
//        .password(passwordEncoder().encode("123456"))
//        .roles("ADMIN")
//        .build();
//    return new InMemoryUserDetailsManager(user);
//  }

  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
