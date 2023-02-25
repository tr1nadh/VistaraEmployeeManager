package com.example.vistaraemployeemanager.logger;

import org.aspectj.lang.JoinPoint;
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
}
