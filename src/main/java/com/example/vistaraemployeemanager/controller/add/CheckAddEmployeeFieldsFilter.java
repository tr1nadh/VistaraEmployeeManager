package com.example.vistaraemployeemanager.controller.add;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

import com.example.vistaraemployeemanager.model.Employee;


@WebFilter("/addEmployee")
public class CheckAddEmployeeFieldsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        var employee = getEmployee(req);

        if (isAnyFieldEmpty(employee)) {
            req.getRequestDispatcher("add").forward(req, res);
            return;
        }

        req.setAttribute("employee", employee);
        filterChain.doFilter(req, res);
    }

    private Employee getEmployee(ServletRequest req) {
        var employee = new Employee();
        employee.setName(req.getParameter("name"));
        employee.setPassword(req.getParameter("password"));
        employee.setEmail(req.getParameter("email"));
        employee.setCountry(req.getParameter("country"));

        return employee;
    }

    private boolean isAnyFieldEmpty(Employee employee) {
        return isStrNullOrEmpty(employee.getName()) || isStrNullOrEmpty(employee.getPassword()) ||
        isStrNullOrEmpty(employee.getEmail()) || isStrNullOrEmpty(employee.getCountry());
    }

    private boolean isStrNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
