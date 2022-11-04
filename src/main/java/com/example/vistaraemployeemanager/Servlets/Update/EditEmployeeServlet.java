package com.example.vistaraemployeemanager.Servlets.Update;

import com.example.vistaraemployeemanager.EmployeeManager.Employee;
import com.example.vistaraemployeemanager.EmployeeManager.EmployeeManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/editEmployee")
public class EditEmployeeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var writer = res.getWriter();
        var employee = getEmployeeDependsOnMethod(req);
        showFields(req, res, writer, employee);
        writer.close();
    }

    private void showFields(HttpServletRequest req, HttpServletResponse res, PrintWriter writer, Employee employee) throws ServletException, IOException {
        req.getRequestDispatcher("editEmployee.html").include(req, res);
        var htmlData = "<div>" +
                "<form action='saveEmployee' method='post'>\n" +
                "  <label class='hide'>Id <input type='text' name='id' value='"+ employee.getId() +"'></label> <br>\n" +
                "\n" +
                "  <label>Name <input type='text' name='name' value='"+ employee.getName() +"'></label> <br>\n" +
                "\n" +
                "  <label>Password <input type='password' name='password' value='"+ employee.getPassword() +"'></label> <br>\n" +
                "\n" +
                "  <label>Email <input type='email' name='email' value='"+ employee.getEmail() +"'></label> <br>\n" +
                "\n" +
                "  <label> Country\n" +
                "    <select name='country'>\n" +
                "      <option value='India'>India</option>\n" +
                "      <option value='UK'>UK</option>\n" +
                "      <option value='USA'>USA</option>\n" +
                "      <option value='UAE'>UAE</option>\n" +
                "      <option value='Other'>Other</option>\n" +
                "    </select>\n" +
                "  </label>\n" +
                "\n" +
                "  <input type='submit' value='Edit & Save'>\n" +
                "</form>" +
                "  <form action=\"view\" method='post'>\n" +
                "    <input type=\"submit\" value=\"View\">\n" +
                "  </form>\n" +
                "</div>";
        writer.println(htmlData);
    }

    private Employee getEmployeeDependsOnMethod(HttpServletRequest req) {
        if (req.getMethod().equals("GET")) {
            var id = Integer.parseInt(req.getParameter("id"));
            var employee = getEmployee(id);
            employee.setId(id);
            return employee;
        }
        else return (Employee) req.getAttribute("employee");
    }

    private Employee getEmployee(int id) {
        Employee employee;
        try {
            employee = EmployeeManager.getEmployee(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }
}
