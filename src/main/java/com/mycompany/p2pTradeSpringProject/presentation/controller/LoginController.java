package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constants.Urls;
import com.mycompany.p2pTradeSpringProject.dto.LoginRequest;
import com.mycompany.p2pTradeSpringProject.exception.BadCredentialsException;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import com.mycompany.p2pTradeSpringProject.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(Urls.LOGIN)
@AllArgsConstructor
public class LoginController { //TODO: Change to Spring Security / change session

    private final AuthService authService;

    @GetMapping
    public String login(HttpServletRequest request) {
        if (request.getSession().getAttribute("authenticatedUser") != null) {
            return "redirect:" + Urls.HOME; // Redirect to home page if user is already authenticated
        }
        return "login";
    }

    @PostMapping
    public String loginPost(LoginRequest loginRequest,
//                            @RequestBody LoginRequest loginRequest Does not work due to convertation error,
                            RedirectAttributes redirectAttributes,
                            HttpServletRequest request) {

        if (request.getSession().getAttribute("authenticatedUser") != null) {
            return "redirect:" + Urls.HOME; // Redirect to home page if user is already authenticated
        }

        try {
            User authenticatedUser = authService.login(loginRequest);
            request.getSession().setAttribute("authenticatedUser", authenticatedUser);
            return "redirect:" + Urls.HOME; // Redirect to home page on successful login

        } catch (BadCredentialsException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:" + Urls.LOGIN; // Redirect back to login page on failure
        }
    }
}