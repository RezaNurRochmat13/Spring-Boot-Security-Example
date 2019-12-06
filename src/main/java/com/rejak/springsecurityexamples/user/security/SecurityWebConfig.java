package com.rejak.springsecurityexamples.user.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    // Create 2 user for demo
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("ADMIN");
    }

    // Secure the endpoints with HTTP Basic Auth
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/users/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/api/v1/user/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/v1/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/user/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
