package com.example.vistaraemployeemanager.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vistaraemployeemanager.dao.EmployeeDao;
import com.example.vistaraemployeemanager.model.Employee;

@Service
public class EmployeeService {

    private boolean hasChange = true;
    private ArrayList<Employee> empList;

    @Autowired
    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public int add(Employee employee) throws Exception {
        hasChange = true;

        var response = employeeDao.addAsync(employee);

        return response.get();
    }

    public int remove(int employeeID) throws Exception {
        hasChange = true;

        var response = employeeDao.removeAsync(employeeID);

        return response.get();
    }

    public int update(int employeeID, Employee employee) throws Exception {
        hasChange = true;

        var response = employeeDao.updateAsync(employeeID, employee);

        return response.get();
    }

    public ArrayList<Employee> getAllEmployees() throws Exception {
        if (!hasChange) return empList;
        
        hasChange = false;

        return employeeDao.getEmployeesAsync().get();
    }

    public Optional<Employee> getEmployee(int employeeID) {
        return employeeDao.getEmployee(employeeID);
    }

    public ArrayList<Employee> findEmployee(String name) {
        return employeeDao.findEmployee(name);
    }

    public ArrayList<Employee> getEmployees(int startFrom, int max) {
        return employeeDao.getEmployees(startFrom, max);
    }
}
