package com.example.vistaraemployeemanager.listener;

import com.example.vistaraemployeemanager.service.SessionService;

import jakarta.servlet.http.HttpSession;

public abstract class Listener {
    
    protected SessionService getSessionManager(HttpSession session) {
        return (SessionService) session.getServletContext().getAttribute("sessionManager");
    }
}
