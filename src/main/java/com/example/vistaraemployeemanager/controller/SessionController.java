package com.example.vistaraemployeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.vistaraemployeemanager.model.Admin;
import com.example.vistaraemployeemanager.service.SessionService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SessionController {

    @Autowired
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    
    @PostMapping("/loginAdmin")
    public RedirectView loginAdmin(Admin admin, HttpServletRequest req) {
        if (admin.getName().equals("admin") && admin.getPassword().equals("pass")) {
            req.getSession().setAttribute("login_status", true);
            return new RedirectView("view");
        }

        return new RedirectView("login");
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest req) {
        req.getSession().setAttribute("login_status", false);
        
        return new RedirectView("login");
    }

    @RequestMapping("/login")
    public ModelAndView showLogin(HttpServletRequest req) {
        if (sessionService.getLoginStatus(req.getSession().getId())) {
            return new ModelAndView("redirect:/view");
        }

        return new ModelAndView("login-admin");
    }
}
