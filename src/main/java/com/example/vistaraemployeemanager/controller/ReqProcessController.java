package com.example.vistaraemployeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import com.example.vistaraemployeemanager.manager.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/")
public class ReqProcessController implements HandlerInterceptor {

    @Autowired
    private final SessionManager manager;

    public ReqProcessController(SessionManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        var sessionId = req.getSession().getId();
        if (!req.getRequestURI().equals("/app/loginAdmin") && manager.getLoginStatus(sessionId) == false) {
            return false;
        }

        return true;
    }
}
