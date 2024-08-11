package com.mycompany.p2pTradeSpringProject.presentation.controller;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class HomeController {

    @RequestMapping({Urls.HOME, Urls.ROOT})
    public String home() {
        return "common/home";
    }

}
