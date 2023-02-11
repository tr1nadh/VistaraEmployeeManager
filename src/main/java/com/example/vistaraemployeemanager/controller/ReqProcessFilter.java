package com.example.vistaraemployeemanager.controller;

import java.io.IOException;
import com.example.vistaraemployeemanager.manager.SessionManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class ReqProcessFilter extends Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var hreq = ((HttpServletRequest) request);
        var sm = getSessionManager(request);
        var sessionID = hreq.getSession().getId();
        if (!hreq.getRequestURI().equals("/app/loginAdmin") && sm.getLoginStatus(sessionID) == false) {
            request.getRequestDispatcher("login").forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }
}
