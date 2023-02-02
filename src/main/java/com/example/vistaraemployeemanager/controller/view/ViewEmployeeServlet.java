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
        var max = 10; 

        var pageStr = getPageValue(req);
        var pageInt = Integer.parseInt(pageStr);
        var startFrom = calculateStartFromValue(pageInt - 1, max);
        var empList = getEmployeeManager().getEmployees(startFrom, max);
        var next = (empList.isEmpty()) ? pageInt : (pageInt + 1);
        var prev = pageInt - 1;
        req.setAttribute("empList", empList);
        req.setAttribute("next", next);
        req.setAttribute("prev", prev);

        req.getRequestDispatcher("view-employee.jsp").forward(req, res);
    }

    private int calculateStartFromValue(int pageInt, int max) {
        pageInt = (pageInt < 0) ? 0 : pageInt;
        return pageInt * max;
    }

    private String getPageValue(HttpServletRequest req) {
        var pageStr = req.getParameter("p");
        return (pageStr == null) ? "1" : pageStr;
    }
}
