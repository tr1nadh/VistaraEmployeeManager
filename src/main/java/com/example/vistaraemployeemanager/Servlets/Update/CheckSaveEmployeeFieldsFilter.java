package com.example.vistaraemployeemanager.Servlets.Update;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

import com.example.vistaraemployeemanager.em.Employee;


@WebFilter("/saveEmployee")
public class CheckSaveEmployeeFieldsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        var employee = getEmployee(req);
        req.setAttribute("employee", employee);

        if (isAnyFieldEmpty(employee)) {
            req.getRequestDispatcher("sign/save-empty-fields.jsp").forward(req, res);
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
        return isFiledNullOrEmpty(employee.getName()) || isFiledNullOrEmpty(employee.getPassword()) ||
                isFiledNullOrEmpty(employee.getEmail()) || isFiledNullOrEmpty(employee.getCountry());
    }

    private boolean isFiledNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
