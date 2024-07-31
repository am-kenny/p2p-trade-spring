package com.mycompany.p2pTradeSpringProject.presentation.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

        @RequestMapping
        public String home() {
            return "home";
        }
}
