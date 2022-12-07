package com.example.vistaraemployeemanager.Servlets.View;


import com.example.vistaraemployeemanager.em.EmployeeManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/view")
public class ViewEmployeeServlet extends HttpServlet {

    // TODO
    /*
     * TODO: After reading data from database, after html page is created,
     * Save it somewhere and when there is change in db, show the saved page then
     * again quering the db.
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
