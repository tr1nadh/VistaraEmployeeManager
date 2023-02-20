package com.example.vistaraemployeemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.vistaraemployeemanager.model.Admin;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SessionController {
    
    @PostMapping("/loginAdmin")
    public RedirectView loginAdmin(Admin admin, HttpServletRequest req) {
        if (admin.getName().equals("admin") && admin.getPassword().equals("pass")) {
            req.getSession().setAttribute("login_status", true);
            return new RedirectView("view");
        }

        return new RedirectView("login");
    }
}
