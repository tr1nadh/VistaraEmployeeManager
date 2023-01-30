package com.example.vistaraemployeemanager.listener;

import com.example.vistaraemployeemanager.manager.SessionManager;

import jakarta.servlet.http.HttpSession;

public abstract class Listener {
    
    protected SessionManager getSessionManager(HttpSession session) {
        return (SessionManager) session.getServletContext().getAttribute("sessionManager");
    }
}
