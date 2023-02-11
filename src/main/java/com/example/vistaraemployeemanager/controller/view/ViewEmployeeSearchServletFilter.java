package com.example.vistaraemployeemanager.controller.view;

import java.io.IOException;

import com.example.vistaraemployeemanager.controller.Filter;
import com.example.vistaraemployeemanager.manager.EmployeeManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/view")
public class ViewEmployeeSearchServletFilter extends Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        var name = req.getParameter("name");
        if (name == null || name.isBlank() || name.isEmpty()) {
            chain.doFilter(req, res);
            return;
        }

        var em = getEmployeeManager(req);
        var empList = em.findEmployee(name);
        req.setAttribute("empList", empList);
        req.setAttribute("next", -1);
        req.getRequestDispatcher("view-employee.jsp").forward(req, res);
    }
}
