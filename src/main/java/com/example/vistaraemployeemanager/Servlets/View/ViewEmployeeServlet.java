package com.example.vistaraemployeemanager.Servlets.View;

import com.example.vistaraemployeemanager.em.Employee;
import com.example.vistaraemployeemanager.em.EmployeeManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/view")
public class ViewEmployeeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            var empList = getEmployees();
            req.setAttribute("empList", empList);
            req.getRequestDispatcher("view-employee.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Employee> getEmployees() throws Exception {
        return (EmployeeManager.hasChange == true) ? EmployeeManager.getAllEmployees() : EmployeeManager.empList;
    }
}
