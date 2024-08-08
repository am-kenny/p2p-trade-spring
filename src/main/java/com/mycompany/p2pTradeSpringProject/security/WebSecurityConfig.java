package com.mycompany.p2pTradeSpringProject.security;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/register", "/login").permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin((form) -> form
                        .loginPage("/login")
                        .failureHandler(customAuthenticationFailureHandler())
                        .permitAll()
                )
                .logout(Customizer.withDefaults());


        return http.build();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider(MyUserDetailService myUserDetailService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(myUserDetailService);
        impl.setHideUserNotFoundExceptions(false);
        impl.setPasswordEncoder(passwordEncoder);
        return impl;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public MyUserDetailService myUserDetailService(IDAOUser daoUser) {
        return new MyUserDetailService(daoUser);
    }
}