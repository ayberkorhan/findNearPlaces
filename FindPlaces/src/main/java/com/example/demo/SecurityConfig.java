package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig   {

    @Bean
    protected InMemoryUserDetailsManager configAuthentication() {
            // authorized users
        List<UserDetails> users = new ArrayList<>();

        List<GrantedAuthority> adminAuthority = new ArrayList<>();
        adminAuthority.add(new SimpleGrantedAuthority("ADMIN"));
        UserDetails admin= new User("admin", "{noop}admin", adminAuthority);
        users.add(admin);

        return new InMemoryUserDetailsManager(users);
    }


    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // which URL will have What access type
        http.authorizeRequests()
               .antMatchers("api/v1/location/**").hasAuthority("ADMIN")//post method
                .antMatchers("api/v1/location").hasAnyAuthority("EMPLOYEE","ADMIN")
                .and().httpBasic(withDefaults())
                .csrf().disable();

        return http.build();


    }


}