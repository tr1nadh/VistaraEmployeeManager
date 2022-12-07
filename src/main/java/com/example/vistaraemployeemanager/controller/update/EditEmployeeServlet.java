package com.example.vistaraemployeemanager.controller.update;

import java.io.IOException;

import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.manager.EmployeeManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/editEmployee")
public class EditEmployeeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var employee = getEmployeeDependsOnMethod(req);
        if (employee == null) throw new IllegalStateException("No user with this ID");
        req.setAttribute("emp", employee);
        req.getRequestDispatcher("edit-employee.jsp").forward(req, res);
    }

    private Employee getEmployeeDependsOnMethod(HttpServletRequest req) {
        if (req.getMethod().equals("GET")) {
            var id = Integer.parseInt(req.getParameter("id"));
            var employee = EmployeeManager.getEmployee(id);;
            if (employee.isPresent()) {
                employee.get().setId(id);
                return employee.get();
            }
            return null;
        }
        else return (Employee) req.getAttribute("employee");
    }
}
