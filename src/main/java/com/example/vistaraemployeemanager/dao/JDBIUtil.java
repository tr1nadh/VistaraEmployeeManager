package com.example.vistaraemployeemanager.dao;

import org.jdbi.v3.core.Jdbi;

public class JDBIUtil {
    
    public static Jdbi getJDBI() {
        return Jdbi.create("jdbc:mysql://localhost:3306/vemdb", "root", System.getenv("DB_PASSWORD")); 
    }
}
