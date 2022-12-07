package com.example.vistaraemployeemanager.controller.Add;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.vistaraemployeemanager.model.em.Employee;
import com.example.vistaraemployeemanager.model.em.EmployeeManager;


@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        var employee = (Employee) req.getAttribute("employee");
        
        EmployeeManager.add(employee);

        req.setAttribute("empName", employee.getName());
        req.getRequestDispatcher("sign/add-employee-success.jsp").include(req, res);
        req.getRequestDispatcher("add").include(req, res);
    }

}
