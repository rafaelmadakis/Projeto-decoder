package com.ead.course.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
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


  @Bean
  public AuthenticationJwtFilter authenticationJwtFilter() {
    return new AuthenticationJwtFilter();
  }


  @Bean
  public RoleHierarchy roleHierarchy() {
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    String hierarchy = "ROLE_ADMIN > ROLE_INSTRUCTOR \n ROLE_INSTRUCTOR > ROLE_STUDENT \n ROLE_STUDENT > ROLE_USER";
    roleHierarchy.setHierarchy(hierarchy);
    return roleHierarchy;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeHttpRequests()

        .anyRequest().authenticated()
        .and()
        .csrf().disable();
//        .formLogin();
    http.addFilterBefore(authenticationJwtFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }


//  @Bean
//  public AuthenticationManager authenticationManager() throws Exception {
//    return authenticationConfiguration.getAuthenticationManager();
//  }

//  @Bean
//  public UserDetailsService userDetailsService() {
//    UserDetails user = User.withUsername("admin")
//        .password(passwordEncoder().encode("123456"))
//        .roles("ADMIN")
//        .build();
//    return new InMemoryUserDetailsManager(user);
//  }
//
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userDetailsService)
//        .passwordEncoder(passwordEncoder());
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }

}
