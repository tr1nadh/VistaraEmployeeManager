package com.example.vistaraemployeemanager.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

// @WebListener
public class AppSessionAttributeListener extends Listener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        var sm = getSessionManager(event.getSession());
        sm.setLoginStatus(event.getSession().getId(), (Boolean)event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        attributeAdded(event);
    }
    
}
