package com.example.vistaraemployeemanager.logger;

public class AppLogger {
    
    private static AppLogger logger = new AppLogger();

    private AppLogger() {}

    public static AppLogger getInstance() {
        return logger;
    }

    public void info(String message) {
        System.out.println("[INFO]: " + message);
    }
}
