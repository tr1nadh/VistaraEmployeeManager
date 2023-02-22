package com.example.vistaraemployeemanager.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.example.vistaraemployeemanager.dao.SessionDao;

@Service
public class SessionService {

    private HashMap<String, Boolean> sessions = new HashMap<>();

    private final SessionDao sessionDao;

    public SessionService(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }
    
    public void saveSession(String sessionID) {
        sessions.putIfAbsent(sessionID, false);
        sessionDao.saveSessionID(sessionID);
    }

    public void removeSession(String sessionID) {
        sessions.remove(sessionID);
        sessionDao.removeSession(sessionID);
    }

    public void setLoginStatus(String sessionID, boolean status) {
        sessions.put(sessionID, status);
        sessionDao.setLoginStatus(sessionID, status);
    }

    public boolean getLoginStatus(String sessionID) {
        if (sessions.containsKey(sessionID)) return sessions.get(sessionID);
        
        var status = sessionDao.getLoginStatus(sessionID);
        sessions.put(sessionID, status);
        return status;
    }
}
