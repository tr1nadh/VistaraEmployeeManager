package com.example.vistaraemployeemanager.Servlets.Update;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.vistaraemployeemanager.em.Employee;
import com.example.vistaraemployeemanager.em.EmployeeManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/editEmployee")
public class EditEmployeeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        var writer = res.getWriter();
        var employee = getEmployeeDependsOnMethod(req);
        if (employee == null) throw new IllegalStateException("No user with this ID");
        showFields(req, res, writer, employee);
        writer.close();
    }

    private void showFields(HttpServletRequest req, HttpServletResponse res, PrintWriter writer, Employee employee) throws ServletException, IOException {
        req.getRequestDispatcher("edit-employee.html").include(req, res);
        var htmlData = "<div class='container border mt-3'>" +
                "      <h2 class='text-center mb-4'>Edit employee</h2>" +
                "<form action='saveEmployee' method='post'>\n" +
                "  <label class='visually-hidden-focusable'>Id <input type='text' name='id' value='"+ employee.getId() +"'></label>\n" +
                "\n" +
                "<div class='form-floating mb-3 mt-3'>" +
                "<input class='form-control' type='name' placeholder='Enter name' name='name' value='"+ employee.getName() +"' />" +
                "  <label>Name</label>\n" +
                "</div>" +
                "\n" +
                "<div class='form-floating mb-3 mt-3'>" +
                "<input class='form-control' type='password' placeholder='Enter password' name='password' value='"+ employee.getPassword() +"' />" +
                "  <label>Password</label>\n" +
                "</div>" +
                "\n" +
                "<div class='form-floating mb-3 mt-3'>" +
                "<input class='form-control' type='email' placeholder='Enter email' name='email' value='"+ employee.getEmail() +"' />" +
                "  <label>Email</label>\n" +
                "</div>"+
                "\n" +
                "  <div class='mb-3 mt-3'>\n" +
                "    <select class='form-select form-select-md' name='country'>\n" +
                "      <option value='India'>India</option>\n" +
                "      <option value='UK'>UK</option>\n" +
                "      <option value='USA'>USA</option>\n" +
                "      <option value='UAE'>UAE</option>\n" +
                "      <option value='Other'>Other</option>\n" +
                "    </select>\n" +
                "  </div>\n" +
                "\n" +
                "  <input class='btn btn-outline-primary' type='submit' value='Edit & Save'>\n" +
                " <a class='btn btn-outline-dark' href='view'>View</a>" +
                "</form>" +
                "</div>";
        writer.println(htmlData);
    }

    private Employee getEmployeeDependsOnMethod(HttpServletRequest req) {
        if (req.getMethod().equals("GET")) {
            var id = Integer.parseInt(req.getParameter("id"));
            var employee = EmployeeManager.getEmployee(id);;
            if (employee.isPresent()) {
                employee.get().setId(id);
                return employee.get();
            }
            return null;
        }
        else return (Employee) req.getAttribute("employee");
    }
}
