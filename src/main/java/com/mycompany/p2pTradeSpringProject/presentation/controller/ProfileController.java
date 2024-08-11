package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import com.mycompany.p2pTradeSpringProject.domain.entity.User;
import com.mycompany.p2pTradeSpringProject.security.CustomUserDetails;
import com.mycompany.p2pTradeSpringProject.service.UserVerificationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String profile(@AuthenticationPrincipal CustomUserDetails userDetails,
                          Model model) { //TODO: Check Model vs ModelAndView

        User user = userDetails.getUser();

        model.addAttribute("authenticatedUser", user);
        model.addAttribute("isVerified", userVerificationService.isVerified(user.getId()));
        return "user/profile";

    }
}
