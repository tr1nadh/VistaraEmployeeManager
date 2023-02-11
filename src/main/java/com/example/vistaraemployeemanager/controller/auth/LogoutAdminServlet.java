package com.example.vistaraemployeemanager.controller.auth;

import java.io.IOException;
import com.example.vistaraemployeemanager.controller.Controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutAdminServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getSession().setAttribute("login_status", false);
        res.sendRedirect("login");
    }
    
    
}
