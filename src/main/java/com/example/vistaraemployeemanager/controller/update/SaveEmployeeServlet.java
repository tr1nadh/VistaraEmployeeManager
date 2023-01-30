package com.example.vistaraemployeemanager.controller.update;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.model.HTTPExchanges;
import com.example.vistaraemployeemanager.controller.ControllerHelper;

@WebServlet("/saveEmployee")
public class SaveEmployeeServlet extends ControllerHelper {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        var employee = getEmployee(req);
        
        updateEmployee(employee);

        alertNForward(new HTTPExchanges(req, res), "Successfully saved changes to the employee of ID: " + employee.getId(), "view");
    }

    private void updateEmployee(Employee employee) {
        try {
            getEmployeeManager().update(employee.getId(), employee);
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
