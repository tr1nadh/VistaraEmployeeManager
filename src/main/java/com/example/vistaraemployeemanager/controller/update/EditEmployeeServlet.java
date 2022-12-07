package com.example.vistaraemployeemanager.controller.update;

import java.io.IOException;

import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.manager.EmployeeManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/editEmployee")
public class EditEmployeeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var empID = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("emp", EmployeeManager.getEmployee(empID));
        req.getRequestDispatcher("edit-employee.jsp").forward(req, res);
    }
}
