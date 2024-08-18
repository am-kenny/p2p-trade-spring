package com.mycompany.p2pTradeSpringProject.presentation.controller.rest;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Order()
public class RestNotFoundController {

    @RequestMapping(path = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public void fallback(HttpServletRequest request) throws NoResourceFoundException {
        throw new NoResourceFoundException(HttpMethod.valueOf(request.getMethod()), request.getRequestURI());
    }

}
