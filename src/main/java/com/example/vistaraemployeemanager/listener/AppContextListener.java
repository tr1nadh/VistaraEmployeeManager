package com.example.vistaraemployeemanager.listener;

import com.example.vistaraemployeemanager.manager.EmployeeManager;
import com.example.vistaraemployeemanager.dao.EmployeeDao;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    
    public void contextInitialized(ServletContextEvent event) {
        var ctx = event.getServletContext();
        
        var employeeManager = new EmployeeManager(new EmployeeDao());

        ctx.setAttribute("employeeManager", employeeManager);
    }
}
