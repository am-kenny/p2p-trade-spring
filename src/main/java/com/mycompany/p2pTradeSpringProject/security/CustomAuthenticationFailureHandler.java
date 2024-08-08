package com.mycompany.p2pTradeSpringProject.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.logging.Logger;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        String errorMessage = "Invalid username or password";

        if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
            errorMessage = "User is disabled";
        } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
            errorMessage = "User account has expired";
        } else if (exception.getMessage().contains("Bad credentials")) { //TODO: Always states wrong password
            errorMessage = "Wrong password";
        } else if (exception.getMessage().contains("User not found")) {
            errorMessage = "User does not exist";
        }

        Logger logger = Logger.getGlobal();
        logger.severe("Authentication failed: " + errorMessage);

        redirectStrategy.sendRedirect(request, response, request.getContextPath() + "/login?error=" + errorMessage);

    }
}
