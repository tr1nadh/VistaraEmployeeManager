package com.example.vistaraemployeemanager.controller.add;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.controller.ControllerHelper;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends ControllerHelper {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        var employee = getEmployee(req);
        
        try {
            getEmployeeManager().add(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("alrtMsg", "Successfully added employee: " + employee.getName());
        req.setAttribute("forwardAddr", "add");
        req.getRequestDispatcher("alert-n-forward.jsp").forward(req, res);
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
