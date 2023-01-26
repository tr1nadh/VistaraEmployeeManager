package com.example.vistaraemployeemanager.controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class ReqProcessFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var hreq = ((HttpServletRequest) request);
        var session = hreq.getSession(false);
        if (!hreq.getRequestURI().equals("/app/loginAdmin") && session == null) {
            request.getRequestDispatcher("login").forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }
    
}
