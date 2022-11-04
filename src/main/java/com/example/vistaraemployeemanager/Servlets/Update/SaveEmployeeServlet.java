package com.example.vistaraemployeemanager.Servlets.Update;

import com.example.vistaraemployeemanager.EmployeeManager.Employee;
import com.example.vistaraemployeemanager.EmployeeManager.EmployeeManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/saveEmployee")
public class SaveEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        var employee = (Employee) req.getAttribute("employee");
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
}
