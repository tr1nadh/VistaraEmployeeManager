package com.example.vistaraemployeemanager.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.example.vistaraemployeemanager.model.Employee;

@Aspect
@Component
public class EmployeeServiceLogger {

    private final AppLogger logger = AppLogger.getInstance();

    @AfterReturning(
        pointcut = "execution (int com.example.vistaraemployeemanager.service.EmployeeService.add(..))",
        returning =  "result")
    public void afterAdd(JoinPoint jp, int result) {
        var employee = (Employee)jp.getArgs()[0];
        if (result == 0) logger.info("Employee added: " + employee.getName());
        else logger.info("Unable to add employee: " + employee.getName());
    }

    @AfterReturning(
        pointcut = "execution (int com.example.vistaraemployeemanager.service.EmployeeService.remove(int))",
        returning =  "result")
    public void afterRemove(JoinPoint jp, int result) {
        var employeeId = (Integer)jp.getArgs()[0];
        if (result == 0) logger.info("Employee removed: " + employeeId);
        else logger.info("Unable to remove employee: " + employeeId);
    }

    @AfterReturning(
        pointcut = "execution (int com.example.vistaraemployeemanager.service.EmployeeService.update(..))",
        returning =  "result")
    public void afterUpdate(JoinPoint jp, int result) {
        var employeeId = (Integer)jp.getArgs()[0];
        if (result == 0) logger.info("Changes saved: " + employeeId);
        else logger.info("Unable to save changes: " + employeeId);
    }

    @After("execution (* com.example.vistaraemployeemanager.service.EmployeeService.getAllEmployees(..))")
    public void afterGettingAllEmployees(JoinPoint jp) {
        logger.info("Successfully retrieved all employees");
    }

    @After("execution (* com.example.vistaraemployeemanager.service.EmployeeService.getEmployee(int))")
    public void afterGettingAnEmployee(JoinPoint jp) {
        var employeeId = (Integer) jp.getArgs()[0];
        logger.info("Successfully retrieved employee of id: " + employeeId);
    }

    @After("execution (* com.example.vistaraemployeemanager.service.EmployeeService.findEmployee(String))")
    public void afterFindingAnEmployeesWithName(JoinPoint jp) {
        var name = (String) jp.getArgs()[0];
        logger.info("Successfully retrieved employees with match name: " + name);
    }

    @After("execution (* com.example.vistaraemployeemanager.service.EmployeeService.getEmployees(int,int))")
    public void afterGettingEmployeesFromRange(JoinPoint jp) {
        var startFrom = (Integer) jp.getArgs()[0];
        var max = (Integer) jp.getArgs()[1];
        logger.info("Successfully retrieved employees from: " + startFrom + " and max: " + max);
    }
}
