package com.example.vistaraemployeemanager.controller.view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.vistaraemployeemanager.controller.ControllerHelper;

@WebServlet("/view")
public class ViewEmployeeServlet extends ControllerHelper {
    
    /*
     * TODO: Download the built view page and then save it somewhere.
     * When there is not change in db, show the page without quering the db
     * again and building view page again.
     */

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        var max = 15; 

        var pageStr = req.getParameter("p");
        if (pageStr == null) pageStr = "0";
        var pageInt = Integer.parseInt(pageStr) - 1;
        pageInt = (pageInt < 0) ? 0 : pageInt;
        var startFrom = max * pageInt;
        pageInt++;
        var next = pageInt + 1;
        var prev = pageInt - 1;

        var empList = getEmployeeManager().getEmployees(startFrom, max);
        if (empList.isEmpty()) --next;
        req.setAttribute("empList", empList);
        req.setAttribute("next", next);
        req.setAttribute("prev", prev);
        req.getRequestDispatcher("view-employee.jsp").forward(req, res);
    }
}
