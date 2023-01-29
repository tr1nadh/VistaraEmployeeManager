package com.example.vistaraemployeemanager.dao;

import org.jdbi.v3.core.Jdbi;

public class SessionDao {

    private final static Jdbi jdbi = JDBIUtil.getJDBI();

    public static void saveSessionID(String ID) {
        jdbi.useHandle(handle -> {
            handle.execute("INSERT INTO session (id) VALUES ('" + ID + "')");
        });
    }

    public static void removeSession(String ID) {
        jdbi.useHandle(handle -> {
            handle.execute("DELETE FROM session WHERE id = '" + ID + "'");
        });
    }

    public static void setLoginStatus(String ID, boolean status) {
        var assigned_status = (status == true) ? 1 : 0;
        jdbi.useHandle(handle -> {
            handle.execute("UPDATE session SET login_status = " + assigned_status + " WHERE id = '" + ID + "'");
        });
    }

    public static boolean getLoginStatus(String ID) {
        var status = jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT login_status FROM session WHERE id = '" + ID + "'").mapTo(Byte.class).findOne();
        });

        if (status.isPresent()) return (status.get() == 1) ? true : false;

        return false;
    }
}
