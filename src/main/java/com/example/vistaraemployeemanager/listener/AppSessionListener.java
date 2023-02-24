package com.example.vistaraemployeemanager.listener;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

// @WebListener
public class AppSessionListener extends Listener implements HttpSessionListener {

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

}
