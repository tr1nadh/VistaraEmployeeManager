package com.example.vistaraemployeemanager.EM;

import com.example.vistaraemployeemanager.EmployeeManager.Database.EmployeeDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeManager {

    public static void add(Employee employee) throws Exception {
        if (EmployeeDao.getAllottedIDs().size() > EmployeeIDGenerator.getIdLimit())
            throw new IllegalStateException("Employee limit reached!!!");

        employee.setId(generateId());
        var response = EmployeeDao.addAsync(employee);
        if (!response.get()) System.out.println("Employee added: " + employee.getName());
        else System.out.println("Unable to add employee: " + employee.getName());
    }

    public static void remove(int employeeId) throws Exception {
        if (!EmployeeDao.getAllottedIDs().contains(employeeId))
            throw new IllegalStateException("Invalid ID");

        var response = EmployeeDao.removeAsync(employeeId);
        if (response.get() > 0) System.out.println("Employee with ID: " + employeeId + " is removed");
        else System.out.println("Unable to remove employee with ID: " + employeeId);
    }

    public static void update(int id, Employee employee) throws Exception {
        if (!EmployeeDao.getAllottedIDs().contains(id))
            throw new IllegalStateException("Invalid ID");

        var response = EmployeeDao.updateAsync(id, employee);
        if (response.get() > 0) System.out.println("Changes saved of ID: " + id);
        else System.out.println("Unable to save changes of ID: " + id);
    }

    public static ArrayList<Employee> getAllEmployees() throws Exception {
        var employees = EmployeeDao.getEmployeesAsync();
        System.out.println("Successfully retrieved employees");
        return employees.get();
    }

    public static Employee getEmployee(int id) throws SQLException {
        if (!EmployeeDao.getAllottedIDs().contains(id))
            throw new IllegalStateException("Invalid ID");

        var employee = EmployeeDao.getEmployee(id);
        System.out.println("Successfully retrieved employee with id: " + id);
        return employee;
    }
    private static int generateId() throws Exception {
        var id = EmployeeIDGenerator.generateID();
        while (EmployeeDao.getAllottedIDsAsync().get().contains(id)) {
            id = EmployeeIDGenerator.generateID();
        }

        return id;
    }

    public static void increaseEmployeeCapacity(int newCapacity) {
        if (newCapacity <= 100)
            throw new IllegalStateException("New capacity cannot be <= 100");

        EmployeeIDGenerator.changeIdLimit(newCapacity);
    }
}
