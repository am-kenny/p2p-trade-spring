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

}