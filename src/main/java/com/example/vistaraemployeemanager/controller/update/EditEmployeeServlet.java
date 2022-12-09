package com.example.vistaraemployeemanager.controller.update;

import java.io.IOException;

import com.example.vistaraemployeemanager.controller.Controller;
import com.example.vistaraemployeemanager.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/editEmployee")
public class EditEmployeeServlet extends Controller {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var employee = getEmployee(req, res);
        if (employee == null) return;
        
        req.setAttribute("emp", employee);
        req.getRequestDispatcher("edit-employee.jsp").forward(req, res);
    }

    private Employee getEmployee(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var empID = Integer.parseInt(req.getParameter("id"));
        var employee = manager.getEmployee(empID);
        if (employee.isEmpty()) {
            req.setAttribute("alrtMsg", "No such employee with ID: " + empID);
            req.setAttribute("forwardAddr", "view");
            req.getRequestDispatcher("alert-n-forward.jsp").forward(req, res);
            return null;
        }
        
        return employee.get();
    }
}
