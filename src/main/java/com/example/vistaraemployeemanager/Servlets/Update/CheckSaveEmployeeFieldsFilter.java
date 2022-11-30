package com.example.vistaraemployeemanager.Servlets.Update;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

import com.example.vistaraemployeemanager.EM.Employee;


@WebFilter("/saveEmployee")
public class CheckSaveEmployeeFieldsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        var employee = getEmployee(req);
        req.setAttribute("employee", employee);

        var writer = res.getWriter();
        if (isAnyFieldEmpty(employee)) {
            writer.println("<script>alert('Fields cannot be empty or blank');</script>");
            req.getRequestDispatcher("editEmployee").include(req, res);
            writer.close();
            return;
        }

        filterChain.doFilter(req, res);
        writer.close();
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
