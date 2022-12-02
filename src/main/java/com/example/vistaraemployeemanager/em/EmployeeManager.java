package com.example.vistaraemployeemanager.em;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.example.vistaraemployeemanager.dao.EmployeeDao;

public class EmployeeManager {

    public static void add(Employee employee) throws Exception {
        var response = EmployeeDao.add(employee);
        if (response > 0) System.out.println("Employee added: " + employee.getName());
        else System.out.println("Unable to add employee: " + employee.getName());
    }

    public static void remove(int employeeId) throws Exception {
        var response = EmployeeDao.removeAsync(employeeId);
        if (response.get() > 0) System.out.println("Employee with ID: " + employeeId + " is removed");
        else System.out.println("Unable to remove employee with ID: " + employeeId);
    }

    public static void update(int id, Employee employee) throws Exception {
        var response = EmployeeDao.updateAsync(id, employee);
        if (response.get() > 0) System.out.println("Changes saved of ID: " + id);
        else System.out.println("Unable to save changes of ID: " + id);
    }

    public static ArrayList<Employee> getAllEmployees() throws Exception {
        var employees = EmployeeDao.getEmployeesAsync();
        System.out.println("Successfully retrieved employees");
        return employees.get();
    }

    public static Optional<Employee> getEmployee(int id) throws SQLException {
        var employee = EmployeeDao.getEmployee(id);
        System.out.println("Successfully retrieved employee with id: " + id);
        return employee;
    }
}
