package com.example.vistaraemployeemanager.controller.update;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.manager.EmployeeManager;


@WebServlet("/saveEmployee")
public class SaveEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        var employee = getEmployee(req);
        updateEmployee(employee);
        res.sendRedirect("view");
    }

    private void updateEmployee(Employee employee) {
        try {
            EmployeeManager.update(employee.getId(), employee);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Employee getEmployee(HttpServletRequest req) {
        var employee = new Employee();
        employee.setId(Integer.parseInt(req.getParameter("id")));
        employee.setName(req.getParameter("name"));
        employee.setPassword(req.getParameter("password"));
        employee.setEmail(req.getParameter("email"));
        employee.setCountry(req.getParameter("country"));

        return employee;
    }
}
