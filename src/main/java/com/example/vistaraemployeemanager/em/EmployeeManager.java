package com.example.vistaraemployeemanager.em;

import java.util.ArrayList;
import java.util.Optional;
import com.example.vistaraemployeemanager.dao.EmployeeDao;

public class EmployeeManager {

    public static void add(Employee employee) {
        var response = EmployeeDao.add(employee);
        if (response > 0)
            System.out.println("Employee added: " + employee.getName());
        else
            System.out.println("Unable to add employee: " + employee.getName());
    }

    public static void remove(int employeeID) throws Exception {
        var response = EmployeeDao.removeAsync(employeeID);
        if (response.get() > 0)
            System.out.println("Employee with ID: " + employeeID + " is removed");
        else
            System.out.println("Unable to remove employee with ID: " + employeeID);
    }

    public static void update(int employeeID, Employee employee) throws Exception {
        var response = EmployeeDao.updateAsync(employeeID, employee);
        if (response.get() > 0)
            System.out.println("Changes saved of ID: " + employeeID);
        else
            System.out.println("Unable to save changes of ID: " + employeeID);
    }

    public static ArrayList<Employee> getAllEmployees() throws Exception {
        var employees = EmployeeDao.getEmployeesAsync();
        System.out.println("Successfully retrieved employees");
        return employees.get();
    }

    public static Optional<Employee> getEmployee(int employeeID) {
        var employee = EmployeeDao.getEmployee(employeeID);
        System.out.println("Successfully retrieved employee with id: " + employeeID);
        return employee;
    }
}
