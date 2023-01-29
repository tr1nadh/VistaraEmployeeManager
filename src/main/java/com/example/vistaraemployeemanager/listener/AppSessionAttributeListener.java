package com.example.vistaraemployeemanager.listener;

import com.example.vistaraemployeemanager.manager.SessionManager;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class AppSessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        var sm = (SessionManager) event.getSession().getServletContext().getAttribute("sessionManager");
        sm.setLoginStatus(event.getSession().getId(), (Boolean)event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        attributeAdded(event);
    }
    
}
