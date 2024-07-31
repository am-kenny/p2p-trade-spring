package com.mycompany.p2pTradeSpringProject.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/logout")
@AllArgsConstructor
public class LogoutController {

        @RequestMapping
        public String logout(RedirectAttributes redirectAttributes,
                             HttpServletRequest request) {
            if (request.getSession().getAttribute("authenticatedUser") != null) {
                request.getSession().removeAttribute("authenticatedUser");
                redirectAttributes.addFlashAttribute("success", "You have been logged out");
            }
            return "redirect:/login";
        }
}
