package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constants.Urls;
import com.mycompany.p2pTradeSpringProject.dto.RegistrationRequest;
import com.mycompany.p2pTradeSpringProject.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(Urls.REGISTER)
@AllArgsConstructor
public class RegisterController {

    private final AuthService authService;

    @GetMapping
    public String register(HttpServletRequest request) {
        if (request.getSession().getAttribute("authenticatedUser") != null) {
            return "redirect:" + Urls.HOME; // Redirect to home page if user is already authenticated
        }
        return "register";
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String registerPost(RegistrationRequest registrationRequest,
                               HttpServletRequest request) {
        if (request.getSession().getAttribute("authenticatedUser") != null) {
            return "redirect:" + Urls.HOME; // Redirect to home page if user is already authenticated
        }

        try {
            authService.register(registrationRequest);
            return "redirect:" + Urls.LOGIN; // Redirect to login page on successful registration
        } catch (Exception e) {
            return "redirect:" + Urls.REGISTER; // Redirect back to register page on failure
        }

    }

}
