package com.example.vistaraemployeemanager.manager;

import java.util.ArrayList;
import java.util.Optional;

import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.dao.EmployeeDao;

public class EmployeeManager {

    private boolean hasChange = true;
    private ArrayList<Employee> empList;


    private final EmployeeDao employeeDao;

    public EmployeeManager(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void add(Employee employee) {
        hasChange = true;

        var response = employeeDao.add(employee);

        if (response > 0) System.out.println("Employee added: " + employee.getName());
        else System.out.println("Unable to add employee: " + employee.getName());
    }

    public void remove(int employeeID) throws Exception {
        hasChange = true;

        var response = employeeDao.removeAsync(employeeID);

        if (response.get() > 0) System.out.println("Employee with ID: " + employeeID + " is removed");
        else System.out.println("Unable to remove employee with ID: " + employeeID);
    }

    public void update(int employeeID, Employee employee) throws Exception {
        hasChange = true;

        var response = employeeDao.updateAsync(employeeID, employee);

        if (response.get() > 0) System.out.println("Changes saved of ID: " + employeeID);
        else System.out.println("Unable to save changes of ID: " + employeeID);
    }

    public ArrayList<Employee> getAllEmployees() throws Exception {
        if (!hasChange) return empList;
        
        hasChange = false;

        empList = employeeDao.getEmployeesAsync().get();
        System.out.println("Successfully retrieved employees");
        
        return empList;
    }

    public Optional<Employee> getEmployee(int employeeID) {
        var employee = employeeDao.getEmployee(employeeID);
        System.out.println("Successfully retrieved employee with id: " + employeeID);
        
        return employee;
    }
}
