package com.example.vistaraemployeemanager.manager;

import java.util.ArrayList;
import java.util.List;
import com.example.vistaraemployeemanager.dao.SessionDao;

public class SessionManager {

    private List<String> currentSessionIDs = new ArrayList<>();
    
    public void saveSessionID(String sessionID) {
        SessionDao.saveSessionID(sessionID);
    }

    public boolean containsSessionID(String sessionID) {
        if (currentSessionIDs.contains(sessionID)) return true;

        var storedID = SessionDao.getSessionID(sessionID);
        if (storedID.isPresent() && storedID.get().equals(sessionID)) {
            currentSessionIDs.add(sessionID);
            return true;
        };

        return false;
    }

    public void removesessionID(String sessionID) {
        currentSessionIDs.remove(sessionID);
        SessionDao.removeSessionID(sessionID);
    }
}
