package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(Urls.LOGIN)
@AllArgsConstructor
public class LoginController {

    @GetMapping
    public String login() {
        return "auth/login";
    }

}