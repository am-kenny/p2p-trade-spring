package com.mycompany.p2pTradeSpringProject.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        String errorMessage = "Something went wrong, please try again";

        if (exception.getMessage().contains("User not found")) {
            errorMessage = "User does not exist";
        } else if (exception.getMessage().contains("Bad credentials")) {
            errorMessage = "Wrong password";
        }

        redirectStrategy.sendRedirect(request, response, request.getContextPath() + "/login?error=" + errorMessage);

    }
}
