package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constants.Urls;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Urls.HOME)
@AllArgsConstructor
public class HomeController {

        @RequestMapping
        public String home() {
            return "home";
        }
}
