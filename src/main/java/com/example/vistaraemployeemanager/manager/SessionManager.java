package com.example.vistaraemployeemanager.manager;

import java.util.HashMap;
import com.example.vistaraemployeemanager.dao.SessionDao;

public class SessionManager {

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
