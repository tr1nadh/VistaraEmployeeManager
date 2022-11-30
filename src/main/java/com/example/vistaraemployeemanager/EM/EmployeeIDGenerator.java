package com.example.vistaraemployeemanager.EmployeeManager;

public class EmployeeIDGenerator {
    // TODO:: Create JSON for this to Manager settings
    private static int idLimit = 100;

    public static int generateID() {
        return (int) (Math.random() * idLimit);
    }

    public static void changeIdLimit(int newIdLimit) {
        idLimit = newIdLimit;
    }

    public static int getIdLimit() {
        return idLimit;
    }
}
