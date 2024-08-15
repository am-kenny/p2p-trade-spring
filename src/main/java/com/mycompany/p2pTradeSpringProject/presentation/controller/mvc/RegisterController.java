package com.mycompany.p2pTradeSpringProject.presentation.controller.mvc;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import com.mycompany.p2pTradeSpringProject.domain.dto.auth.request.RegistrationRequest;
import com.mycompany.p2pTradeSpringProject.domain.dto.auth.response.RegistrationResponse;
import com.mycompany.p2pTradeSpringProject.domain.dto.common.Error;
import com.mycompany.p2pTradeSpringProject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(Urls.REGISTER)
@AllArgsConstructor
public class RegisterController {

    private final AuthService authService;

    @GetMapping
    public String register(@ModelAttribute("errors") ArrayList<Error> errors) {

        return "auth/register";
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String registerPost(RegistrationRequest registrationRequest,
                               RedirectAttributes redirectAttributes) {

        try {
            RegistrationResponse response = authService.register(registrationRequest);

            if (!response.isSuccess()) {
                redirectAttributes.addFlashAttribute("errors", response.getErrors());
                return "redirect:" + Urls.REGISTER; // Redirect back to register page on failure
            }
            return "redirect:" + Urls.LOGIN; // Redirect to login page on successful registration
        } catch (Exception e) {

            Error error = Error.builder()
                    .message(e.getMessage())
                    .build();

            List<Error> errors = List.of(error);

            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:" + Urls.REGISTER; // Redirect back to register page on failure
        }

    }

}
