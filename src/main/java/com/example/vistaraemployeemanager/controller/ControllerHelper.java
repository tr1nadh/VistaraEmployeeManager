package com.example.vistaraemployeemanager.controller;

import com.example.vistaraemployeemanager.manager.EmployeeManager;

import jakarta.servlet.http.HttpServlet;

public abstract class ControllerHelper extends HttpServlet {
    protected EmployeeManager getEmployeeManager() {
        return (EmployeeManager) getServletContext().getAttribute("employeeManager");
    }
}
