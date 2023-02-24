package com.example.vistaraemployeemanager;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.vistaraemployeemanager.model.Employee;

@Aspect
@Component
public class EmployeeServiceLogger {

    @AfterReturning(
        pointcut = "execution (int com.example.vistaraemployeemanager.service.EmployeeService.add(..))",
        returning =  "result")
    public void afterAdd(JoinPoint jp, int result) {
        var employee = (Employee)jp.getArgs()[0];
        if (result == 0) System.out.println("Employee added: " + employee.getName());
        else System.out.println("Unable to add employee: " + employee.getName());
    }

    @AfterReturning(
        pointcut = "execution (int com.example.vistaraemployeemanager.service.EmployeeService.remove(int))",
        returning =  "result")
    public void afterRemove(JoinPoint jp, int result) {
        var employeeId = (Integer)jp.getArgs()[0];
        if (result == 0) System.out.println("Employee with ID: " + employeeId + " is removed");
        else System.out.println("Unable to remove employee with ID: " + employeeId);
    }

    @AfterReturning(
        pointcut = "execution (int com.example.vistaraemployeemanager.service.EmployeeService.update(..))",
        returning =  "result")
    public void afterUpdate(JoinPoint jp, int result) {
        var employeeId = (Integer)jp.getArgs()[0];
        if (result == 0) System.out.println("Changes saved of ID: " + employeeId);
        else System.out.println("Unable to save changes of ID: " + employeeId);
    }
}
