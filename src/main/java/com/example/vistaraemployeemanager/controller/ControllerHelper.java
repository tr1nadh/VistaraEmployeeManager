package com.example.vistaraemployeemanager.controller;

import com.example.vistaraemployeemanager.manager.EmployeeManager;
import com.example.vistaraemployeemanager.manager.SessionManager;
import jakarta.servlet.http.HttpServlet;

public abstract class ControllerHelper extends HttpServlet {
    
    protected SessionManager getSessionManager() {
        return (SessionManager) getServletContext().getAttribute("sessionManager");
    }

    protected EmployeeManager getEmployeeManager() {
        return (EmployeeManager) getServletContext().getAttribute("employeeManager");
    }
}
