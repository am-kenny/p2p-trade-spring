package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.dto.RegistrationRequest;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import com.mycompany.p2pTradeSpringProject.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private final AuthService authService;

    @GetMapping
    public String register(HttpServletRequest request) {
        if (request.getSession().getAttribute("authenticatedUser") != null) {
            return "redirect:/home"; // Redirect to home page if user is already authenticated
        }
        return "register";
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String registerPost(RegistrationRequest registrationRequest,
                               HttpServletRequest request) {
        if (request.getSession().getAttribute("authenticatedUser") != null) {
            return "redirect:/home"; // Redirect to home page if user is already authenticated
        }

        try {
            authService.register(registrationRequest);
            return "redirect:/login"; // Redirect to login page on successful registration
        } catch (Exception e) {
            return "redirect:/register"; // Redirect back to register page on failure
        }

    }

}
