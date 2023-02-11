package com.example.vistaraemployeemanager.controller.auth;

import java.io.IOException;
import com.example.vistaraemployeemanager.controller.Controller;
import com.example.vistaraemployeemanager.model.Admin;
import com.example.vistaraemployeemanager.model.HTTPExchanges;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginAdmin")
public class LoginAdminServlet extends Controller {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        var admin = getAdmin(req);
        if (admin.getName().equals("admin") && admin.getPassword().equals("pass")) {
            req.getSession().setAttribute("login_status", true);
            res.sendRedirect("view");
            return;
        }

        alertNForward(new HTTPExchanges(req, res), "Name or password is wrong", "login");
    }

    private Admin getAdmin(HttpServletRequest req) {
        return new Admin(req.getParameter("name"), req.getParameter("password"));
    }
}
