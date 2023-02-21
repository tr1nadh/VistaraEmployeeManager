package com.example.vistaraemployeemanager.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    
    public void contextInitialized(ServletContextEvent event) {
        var ctx = event.getServletContext();
        ctx.getSessionCookieConfig().setMaxAge(24 * 60 * 60);
    }
}
