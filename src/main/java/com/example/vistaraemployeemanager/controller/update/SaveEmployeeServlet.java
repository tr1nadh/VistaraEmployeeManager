package com.example.vistaraemployeemanager.controller.update;

import jakarta.servlet.ServletException;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        var employee = getEmployee(req);
        updateEmployee(employee);

        req.setAttribute("alrtMsg", "Successfully saved changes to the employee of ID: " + employee.getId());
        req.setAttribute("forwardAddr", "view");
        req.getRequestDispatcher("alert-n-forward.jsp").forward(req, res);
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
