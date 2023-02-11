package com.example.vistaraemployeemanager.controller;

import java.io.IOException;
import com.example.vistaraemployeemanager.manager.EmployeeManager;
import com.example.vistaraemployeemanager.manager.SessionManager;
import com.example.vistaraemployeemanager.model.HTTPExchanges;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public abstract class Controller extends HttpServlet {

    protected SessionManager getSessionManager() {
        return (SessionManager) getServletContext().getAttribute("sessionManager");
    }
    
    protected EmployeeManager getEmployeeManager() {
        return (EmployeeManager) getServletContext().getAttribute("employeeManager");
    }

    protected void alertNForward(HTTPExchanges hex, String alertMessage, String forwardAddress) throws ServletException, IOException {
        var req = hex.getReq();
        var res = hex.getRes();

        req.setAttribute("alrtMsg", alertMessage);
        req.setAttribute("forwardAddr", forwardAddress);
        req.getRequestDispatcher("alert-n-forward.jsp").forward(req, res);
    }

}
