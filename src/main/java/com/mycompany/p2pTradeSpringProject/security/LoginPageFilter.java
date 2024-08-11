package com.mycompany.p2pTradeSpringProject.security;

import com.mycompany.p2pTradeSpringProject.constant.Urls;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class LoginPageFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && (
                (((HttpServletRequest)request).getRequestURI().equals(Urls.LOGIN))
                || (((HttpServletRequest)request).getRequestURI().equals(Urls.REGISTER))
                )){

            ((HttpServletResponse)response).sendRedirect(Urls.HOME);
        }
        chain.doFilter(request, response);
    }
}
