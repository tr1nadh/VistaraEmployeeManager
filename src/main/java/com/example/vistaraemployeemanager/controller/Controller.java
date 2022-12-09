package com.example.vistaraemployeemanager.controller;

import com.example.vistaraemployeemanager.manager.EmployeeManager;

import jakarta.servlet.http.HttpServlet;

public abstract class Controller extends HttpServlet {
    protected EmployeeManager manager = (EmployeeManager) getServletContext().getAttribute("employeeManager");
}
