package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constants.Urls;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import com.mycompany.p2pTradeSpringProject.security.MyUserDetails;
import com.mycompany.p2pTradeSpringProject.service.UserVerificationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Urls.PROFILE)
@AllArgsConstructor
public class ProfileController {

    private final UserVerificationService userVerificationService;

    @GetMapping
    public String profile(HttpServletRequest request, //TODO: remove HttpServletRequest
                          Model model) { //TODO: Check Model vs ModelAndView

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof MyUserDetails) {
            User user = ((MyUserDetails) principal).getUser();

            model.addAttribute("authenticatedUser", user);
            model.addAttribute("isVerified", userVerificationService.isVerified(user.getId()));
            return "profile";
        }

        return "redirect:" + Urls.LOGIN;
    }
}
