package com.example.vistaraemployeemanager.controller.add;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.manager.EmployeeManager;


@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        var employee = (Employee) req.getAttribute("employee");
        
        EmployeeManager.add(employee);

        // TODO: Two pages should'nt be included, find alternative way for showing alert.
        req.setAttribute("empName", employee.getName());
        req.getRequestDispatcher("sign/add-employee-success.jsp").include(req, res);
        req.getRequestDispatcher("add").include(req, res);
    }

}
