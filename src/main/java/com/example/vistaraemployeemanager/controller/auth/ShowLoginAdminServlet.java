package com.example.vistaraemployeemanager.controller.auth;

import java.io.IOException;
import com.example.vistaraemployeemanager.controller.ControllerHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class ShowLoginAdminServlet extends ControllerHelper {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        if (getSessionManager().getLoginStatus(req.getSession().getId())) {
            res.sendRedirect("view");
            return;
        }

        req.getRequestDispatcher("login-admin.html").include(req, res);
    }
}
