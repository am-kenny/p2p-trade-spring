package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import com.mycompany.p2pTradeSpringProject.service.UserVerificationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private final UserVerificationService userVerificationService;

    @GetMapping
    public String profile(HttpServletRequest request,
                          Model model) { //TODO: Check Model vs ModelAndView
        if (request.getSession().getAttribute("authenticatedUser") == null) {
            return "redirect:/login"; // Redirect to login page if user is not authenticated
        }

        User user = (User) request.getSession().getAttribute("authenticatedUser");
        model.addAttribute("isVerified", userVerificationService.isVerified(user.getId()));
        return "profile";
    }
}
