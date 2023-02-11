package com.example.vistaraemployeemanager.controller;

import com.example.vistaraemployeemanager.manager.EmployeeManager;
import com.example.vistaraemployeemanager.manager.SessionManager;
import jakarta.servlet.ServletRequest;

public abstract class Filter implements jakarta.servlet.Filter {

    protected SessionManager getSessionManager(ServletRequest request) {
        return (SessionManager) request.getServletContext().getAttribute("sessionManager");
    }
    
    protected EmployeeManager getEmployeeManager(ServletRequest request) {
        return (EmployeeManager) request.getServletContext().getAttribute("employeeManager");
    }
}
