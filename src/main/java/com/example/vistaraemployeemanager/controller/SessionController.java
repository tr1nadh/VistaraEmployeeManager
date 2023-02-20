package com.example.vistaraemployeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.vistaraemployeemanager.manager.SessionManager;
import com.example.vistaraemployeemanager.model.Admin;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SessionController {

    @Autowired
    private final SessionManager manager;

    public SessionController(SessionManager manager) {
        this.manager = manager;
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
        if (manager.getLoginStatus(req.getSession().getId())) {
            return new ModelAndView("redirect:/view");
        }

        return new ModelAndView("login-admin.jsp");
    }
}
