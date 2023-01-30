package com.example.vistaraemployeemanager.controller.auth;

import java.io.IOException;
import com.example.vistaraemployeemanager.controller.ControllerHelper;
import com.example.vistaraemployeemanager.model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginAdmin")
public class LoginAdminServlet extends ControllerHelper {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        var admin = getAdmin(req);
        if (admin.getName().equals("admin") && admin.getPassword().equals("pass")) {
            req.getSession().setAttribute("login_status", true);
            res.sendRedirect("view");
            return;
        }

        req.setAttribute("alrtMsg", "Name or password is wrong");
        req.setAttribute("forwardAddr", "login");
        req.getRequestDispatcher("alert-n-forward.jsp").forward(req, res);
    }

    private Admin getAdmin(HttpServletRequest req) {
        return new Admin(req.getParameter("name"), req.getParameter("password"));
    }
}
