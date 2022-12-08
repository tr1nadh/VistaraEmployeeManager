package com.example.vistaraemployeemanager.controller.update;

import java.io.IOException;

import com.example.vistaraemployeemanager.manager.EmployeeManager;
import com.example.vistaraemployeemanager.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/editEmployee")
public class EditEmployeeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setAttribute("emp", getEmployee(req));
        req.getRequestDispatcher("edit-employee.jsp").forward(req, res);
    }

    private Employee getEmployee(HttpServletRequest req) {
        var empID = Integer.parseInt(req.getParameter("id"));
        var employee = EmployeeManager.getEmployee(empID);
        if (employee.isEmpty()) throw new IllegalStateException("No such employee with ID: " + empID);
        
        return employee.get();
    }
}
