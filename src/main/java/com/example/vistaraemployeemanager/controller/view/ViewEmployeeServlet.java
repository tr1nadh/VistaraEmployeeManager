package com.example.vistaraemployeemanager.controller.view;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.vistaraemployeemanager.manager.EmployeeManager;


@WebServlet("/view")
public class ViewEmployeeServlet extends HttpServlet {
    
    /*
     * TODO: Download the built view page and then save it somewhere.
     * When there is not change in db, show the page without quering the db
     * again and building view page again.
     */

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            var empList = EmployeeManager.getAllEmployees();
            req.setAttribute("empList", empList);
            req.getRequestDispatcher("view-employee.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
