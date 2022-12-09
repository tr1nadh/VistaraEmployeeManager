package com.example.vistaraemployeemanager.listener;

import com.example.vistaraemployeemanager.database.JDBIManager;
import com.example.vistaraemployeemanager.manager.EmployeeManager;
import com.example.vistaraemployeemanager.dao.EmployeeDao;
import com.example.vistaraemployeemanager.database.HikariCPDatasource;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    
    public void contextInitialized(ServletContextEvent event) {
        var ctx = event.getServletContext();

        var datasource = new HikariCPDatasource();
        var JDBIManager = new JDBIManager(datasource);
        var employeeDao = new EmployeeDao(JDBIManager);
        var employeeManager = new EmployeeManager(employeeDao);

        ctx.setAttribute("employeeManager", employeeManager);
    }
}
