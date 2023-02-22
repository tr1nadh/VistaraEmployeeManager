package com.example.vistaraemployeemanager.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.example.vistaraemployeemanager.dao.SessionDao;

@Service
public class SessionService {

    private HashMap<String, Boolean> sessions = new HashMap<>();
    
    public void saveSession(String sessionID) {
        sessions.putIfAbsent(sessionID, false);
        SessionDao.saveSessionID(sessionID);
    }

    public void removeSession(String sessionID) {
        sessions.remove(sessionID);
        SessionDao.removeSession(sessionID);
    }

    public void setLoginStatus(String sessionID, boolean status) {
        sessions.put(sessionID, status);
        SessionDao.setLoginStatus(sessionID, status);
    }

    public boolean getLoginStatus(String sessionID) {
        if (sessions.containsKey(sessionID)) return sessions.get(sessionID);
        
        var status = SessionDao.getLoginStatus(sessionID);
        sessions.put(sessionID, status);
        return status;
    }
}
