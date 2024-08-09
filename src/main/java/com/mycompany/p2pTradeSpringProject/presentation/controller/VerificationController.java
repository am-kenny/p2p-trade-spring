package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constants.Urls;
import com.mycompany.p2pTradeSpringProject.dto.VerificationRequest;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import com.mycompany.p2pTradeSpringProject.security.CustomUserDetails;
import com.mycompany.p2pTradeSpringProject.service.UserVerificationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(Urls.VERIFY)
@AllArgsConstructor
public class VerificationController {

    private final UserVerificationService userVerificationService;

    @GetMapping
    public String verify(@AuthenticationPrincipal CustomUserDetails userDetails) {

        boolean isVerified = userVerificationService.isVerified(userDetails.getUser().getId());

        if (isVerified) {
            return "redirect:" + Urls.PROFILE; // Redirect to profile page if user is already verified
        }

        return "verify";
    }

    @PostMapping
    public String verifyPost(VerificationRequest verificationRequest,
                             @AuthenticationPrincipal CustomUserDetails userDetails) {

        boolean isVerified = userVerificationService.isVerified(userDetails.getUser().getId());

        if (isVerified) {
            return "redirect:" + Urls.PROFILE; // Redirect to profile page if user is already verified
        }

        User user = userDetails.getUser();
        userVerificationService.verifyUser(user.getId(), verificationRequest);

        return "redirect:" + Urls.PROFILE; // Redirect to profile page on success
    }

}
