package com.mycompany.p2pTradeSpringProject.security;

import com.mycompany.p2pTradeSpringProject.constants.Urls;
import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(Urls.ROOT, Urls.HOME, Urls.REGISTER, Urls.LOGIN).permitAll()
                        .anyRequest().authenticated()
                )

                .formLogin((form) -> form
                        .loginPage(Urls.LOGIN)
                        .failureHandler(customAuthenticationFailureHandler())
                        .permitAll()
                ).logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher(Urls.LOGOUT, "GET"))
                        .logoutSuccessUrl(Urls.LOGIN + "?logout")
                );


        return http.build();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider(MyUserDetailService myUserDetailService, PasswordEncoder passwordEncoder) { // This bean is made because of need to set hideUserNotFoundExceptions to false
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