package com.example.vistaraemployeemanager.Servlets.Delete;

import com.example.vistaraemployeemanager.EmployeeManager.EmployeeManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        var id = Integer.parseInt(req.getParameter("id"));
        removeEmployee(id);
        res.sendRedirect("view");
    }

    private void removeEmployee(int id) {
        try {
            EmployeeManager.remove(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
