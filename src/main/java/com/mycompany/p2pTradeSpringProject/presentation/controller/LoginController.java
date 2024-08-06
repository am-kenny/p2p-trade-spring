package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constants.Urls;
import com.mycompany.p2pTradeSpringProject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(Urls.LOGIN)
@AllArgsConstructor
public class LoginController {

    private final AuthService authService;

    @GetMapping
    public String login() {
        return "login";
    }

//    @PostMapping
//    public String loginPost(LoginRequest loginRequest,
//                            RedirectAttributes redirectAttributes,
//                            HttpServletRequest request) {
//
//
//        try {
//            User authenticatedUser = authService.login(loginRequest);
//            request.getSession().setAttribute("authenticatedUser", authenticatedUser);
//            return "redirect:" + Urls.HOME; // Redirect to home page on successful login
//
//        } catch (BadCredentialsException e) {
//            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
//            return "redirect:" + Urls.LOGIN; // Redirect back to login page on failure
//        }
//    }
}