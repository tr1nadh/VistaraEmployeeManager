package com.example.vistaraemployeemanager.controller;

import com.example.vistaraemployeemanager.manager.EmployeeManager;

import jakarta.servlet.http.HttpServlet;

public abstract class IController extends HttpServlet {
    protected EmployeeManager manager = (EmployeeManager) getServletContext().getAttribute("employeeManager");
}
