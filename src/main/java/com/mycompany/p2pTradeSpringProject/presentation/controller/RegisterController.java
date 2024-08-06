package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constants.Urls;
import com.mycompany.p2pTradeSpringProject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(Urls.REGISTER)
@AllArgsConstructor
public class RegisterController {

    private final AuthService authService;

    @GetMapping
    public String register() {
        return "register";
    }

//    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    public String registerPost(RegistrationRequest registrationRequest,
//                               HttpServletRequest request) {

//        try {
//            authService.register(registrationRequest);
//            return "redirect:" + Urls.LOGIN; // Redirect to login page on successful registration
//        } catch (Exception e) {
//            return "redirect:" + Urls.REGISTER; // Redirect back to register page on failure
//        }
//
//    }

}
