package com.example.vistaraemployeemanager.controller.delete;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.vistaraemployeemanager.controller.Controller;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        var empID = Integer.parseInt(req.getParameter("id"));
        removeEmployee(empID);
        res.sendRedirect("view");
    }

    private void removeEmployee(int id) {
        try {
            getEmployeeManager().remove(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
