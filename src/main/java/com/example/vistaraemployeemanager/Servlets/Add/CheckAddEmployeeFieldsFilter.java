package com.example.vistaraemployeemanager.Servlets.Add;

import com.example.vistaraemployeemanager.EmployeeManager.Employee;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;


@WebFilter("/addEmployee")
public class CheckAddEmployeeFieldsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        res.setContentType("text/html");
        var employee = getEmployee(req);

        var writer = res.getWriter();
        if (isAnyFieldEmpty(employee)) {
            writer.println("<script>alert('Fields cannot be empty or blank');</script>");
            req.getRequestDispatcher("/add").include(req, res);
            writer.close();
            return;
        }

        req.setAttribute("employee", employee);
        filterChain.doFilter(req, res);
        writer.close();
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
        return isFiledNullOrEmpty(employee.getName()) || isFiledNullOrEmpty(employee.getPassword()) ||
                isFiledNullOrEmpty(employee.getEmail()) || isFiledNullOrEmpty(employee.getCountry());
    }

    private boolean isFiledNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
