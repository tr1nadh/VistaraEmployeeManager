package com.example.vistaraemployeemanager.controller.add;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.model.HTTPExchanges;
import com.example.vistaraemployeemanager.controller.Controller;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends Controller {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        var employee = getEmployee(req);
        
        try {
            getEmployeeManager().add(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        alertNForward(new HTTPExchanges(req, res), "Successfully added employee: " + employee.getName(), "add");
    }

    private Employee getEmployee(HttpServletRequest req) {
        var employee = new Employee();
        employee.setName(req.getParameter("name"));
        employee.setPassword(req.getParameter("password"));
        employee.setEmail(req.getParameter("email"));
        employee.setCountry(req.getParameter("country"));

        return employee;
    }

}
