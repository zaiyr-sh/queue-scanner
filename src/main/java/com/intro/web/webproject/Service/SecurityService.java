package com.intro.web.webproject.Service;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityService extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                authorizeRequests()
                .antMatchers("/", "/api/client/", "/api/client", "/api/client/all/", "/api/client/all", "/api/client/ip/", "/api/client/ip", "/api/client/ip/{ipAddress}", "/api/client/ip/{ipAddress}/",
                        "/api/employee/", "/api/employee", "/api/employee/{id}", "/api/employee/{id}/", "/api/employee/all/", "/api/employee/all", "/api/employee/queue/", "/api/employee/queue",
                        "/api/queue/", "/api/queue", "/api/queue/all/", "/api/queue/all", "/api/queue/{id}", "/api/queue/{id}/").permitAll()
                .antMatchers("/**").access("hasRole('Employers Administration')")
                .and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}password")
                .roles("Employers Administration");
    }

}
