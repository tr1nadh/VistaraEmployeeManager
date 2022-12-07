package com.example.vistaraemployeemanager.controller.update;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

import com.example.vistaraemployeemanager.model.Employee;


@WebFilter("/saveEmployee")
public class CheckSaveEmployeeFieldsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        var employee = getEmployee(req);
        req.setAttribute("employee", employee);

        if (isAnyFieldEmpty(employee)) {
            req.getRequestDispatcher("sign/empty-fields-error.jsp").include(req, res);
            req.getRequestDispatcher("editEmployee").include(req, res);
            return;
        }

        filterChain.doFilter(req, res);
    }

    private Employee getEmployee(ServletRequest req) {
        var employee = new Employee();
        employee.setId(Integer.parseInt(req.getParameter("id")));
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
