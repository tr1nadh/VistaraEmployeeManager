package com.example.vistaraemployeemanager.dao;

import java.util.Optional;
import org.jdbi.v3.core.Jdbi;

public class SessionDao {

    private final static Jdbi jdbi = JDBIUtil.getJDBI();

    public static void saveSessionID(String ID) {
        jdbi.useHandle(handle -> {
            handle.execute("INSERT INTO session VALUES ('" + ID + "')");
        });
    }

    public static Optional<String> getSessionID(String ID) {
        return jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT id FROM session WHERE id = '" + ID + "'").mapTo(String.class).findOne();
        });
    }

    public static void removeSessionID(String ID) {
        jdbi.useHandle(handle -> {
            handle.execute("DELETE FROM session WHERE id = '" + ID + "'");
        });
    }
}
