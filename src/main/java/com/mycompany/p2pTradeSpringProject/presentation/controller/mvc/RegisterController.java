package com.mycompany.p2pTradeSpringProject.presentation.controller.mvc;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import com.mycompany.p2pTradeSpringProject.domain.dto.auth.request.RegistrationRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.ValidationError;
import com.mycompany.p2pTradeSpringProject.exception.custom.ValidationException;
import com.mycompany.p2pTradeSpringProject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Controller
@RequestMapping(Urls.REGISTER)
@AllArgsConstructor
public class RegisterController {

    private final AuthService authService;

    @GetMapping
    public String register(@ModelAttribute("errors") ArrayList<ValidationError> errors) {
        return "auth/register";
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String registerPost(RegistrationRequest registrationRequest,
                               RedirectAttributes redirectAttributes) {
        try {
            Integer userId = authService.register(registrationRequest);
            return "redirect:" + Urls.LOGIN; // Redirect to login page on successful registration
        } catch (ValidationException e) {
            redirectAttributes.addFlashAttribute("errors", e.getValidationErrors());
            return "redirect:" + Urls.REGISTER; // Redirect back to register page on failure
        }
    }
}
