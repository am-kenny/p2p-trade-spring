package com.mycompany.p2pTradeSpringProject.presentation.controller.mvc;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import com.mycompany.p2pTradeSpringProject.domain.dto.userprofile.request.VerificationRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.userprofile.response.VerificationResponse;
import com.mycompany.p2pTradeSpringProject.domain.entity.User;
import com.mycompany.p2pTradeSpringProject.security.CustomUserDetails;
import com.mycompany.p2pTradeSpringProject.service.UserVerificationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Controller
@RequestMapping(Urls.VERIFY)
@AllArgsConstructor
public class VerificationController {

    private final UserVerificationService userVerificationService;

    @GetMapping
    public String verify(@AuthenticationPrincipal CustomUserDetails userDetails,
                         @ModelAttribute("errors") ArrayList<ValidationError> errors) {

        boolean isVerified = userVerificationService.isVerified(userDetails.getUser().getId());

        if (isVerified) {
            return "redirect:" + Urls.PROFILE; // Redirect to profile page if user is already verified
        }

        return "user/verify";
    }

    @PostMapping
    public String verifyPost(VerificationRequest verificationRequest,
                             @AuthenticationPrincipal CustomUserDetails userDetails,
                             RedirectAttributes redirectAttributes) {

        boolean isVerified = userVerificationService.isVerified(userDetails.getUser().getId());

        if (isVerified) {
            return "redirect:" + Urls.PROFILE; // Redirect to profile page if user is already verified
        }

        User user = userDetails.getUser();
        VerificationResponse response = userVerificationService.verifyUser(user.getId(), verificationRequest);

        if (!response.isSuccess()) {
            redirectAttributes.addFlashAttribute("errors", response.getErrors());
            return "redirect:" + Urls.VERIFY; // Redirect back to verification page on failure
        }

        return "redirect:" + Urls.PROFILE; // Redirect to profile page on success
    }

}
