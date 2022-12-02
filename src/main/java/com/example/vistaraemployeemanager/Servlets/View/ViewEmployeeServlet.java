package com.example.vistaraemployeemanager.Servlets.View;

import com.example.vistaraemployeemanager.em.EmployeeManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/view")
public class ViewEmployeeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        var writer = res.getWriter();
        req.getRequestDispatcher("view-employee.html").include(req, res);
        printEmployeesTable(writer);
        writer.close();
    }

    private void addAddEmployeeBTN(PrintWriter writer) {
        var addBTN = """
                <form action='add' method='post'>
                <button class='btn btn-outline-primary' type='submit'>Add</button>
                </form>
                """;
        writer.println(addBTN);
    }

    private void printEmployeesTable(PrintWriter writer) {
        printEmployeesTableHead(writer);
        printEmployeesTableBody(writer);
    }

    private void printEmployeesTableBody(PrintWriter writer) {
        try {
            printEmployees(writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        addAddEmployeeBTN(writer);
        writer.println("</tbody></table></div>");
    }

    private void printEmployeesTableHead(PrintWriter writer) {
        var tableHead = """
                <div class='container border mt-3'>
                <h2 class='text-center mb-4'>View Employees</h2>
                <table class='table table-hover'>
                <tbody>
                <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Password</th>
                <th>Email</th>
                <th>Country</th>
                <th>Edit</th>
                <th>Delete</th>
                </tr>
                """;
        writer.print(tableHead);
    }

    private void printEmployees(PrintWriter writer) throws Exception {
        var empList = EmployeeManager.getAllEmployees();
        for (var emp : empList) {
            var id = emp.getId();
            writer.print("<tr>\n" +
                    "<td>" + id + "</td>\n" +
                    "<td>" + emp.getName() + "</td>\n" +
                    "<td>" + emp.getPassword() + "</td>\n" +
                    "<td>" + emp.getEmail() + "</td>\n" +
                    "<td>" + emp.getCountry() + "</td>\n" +
                    "<td> <a class='btn btn-success btn-sm' href='editEmployee?id=" + id + "'>Edit</a> </td>\n" +
                    "<td> <a class='btn btn-danger btn-sm' href='deleteEmployee?id=" + id + "'>Delete</a> </td>\n" +
                    "</tr>");
        }
    }
}
