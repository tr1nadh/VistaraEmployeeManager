package com.example.vistaraemployeemanager.listener;

import com.example.vistaraemployeemanager.manager.SessionManager;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AppSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        var session = se.getSession();
        session.setMaxInactiveInterval(24 * 60 * 60);
        getSessionManager(session).saveSession(session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        var session = se.getSession();
        getSessionManager(session).removeSession(session.getId());
    }

    private SessionManager getSessionManager(HttpSession session) {
        return (SessionManager) session.getServletContext().getAttribute("sessionManager");
    }

}
