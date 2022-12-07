package com.example.vistaraemployeemanager.controller.Add;

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
            req.getRequestDispatcher("sign/add-empty-fields-error.jsp").include(req, res);
            req.getRequestDispatcher("add").include(req, res);
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
        return employee.isNameEmptyOrNull() || employee.isPasswordEmptyOrNull() ||
                employee.isEmailEmptyOrNull() || employee.isCountryEmptyOrNull();
    }
}
