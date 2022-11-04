package com.example.vistaraemployeemanager.EmployeeManager.Database;

import com.example.vistaraemployeemanager.EmployeeManager.Employee;

public class EmployeeDBQueryManager {
    private static final String TABLE_NAME = "Employee";
    public static String getInsertQuery(Employee employee) {
        return "INSERT INTO "+ TABLE_NAME +" VALUES (" +
                ""+ employee.getId() +", '"+ employee.getName() +"', " +
                "'"+ employee.getPassword() +"', '"+ employee.getEmail() +"', " +
                "'"+ employee.getCountry() +"')";
    }

    public static String getDeleteQuery(int id) {
        return "DELETE FROM "+ TABLE_NAME +" WHERE Id = " + id;
    }

    public static String getUpdateQuery(int id, Employee employee) {
        return "UPDATE "+ TABLE_NAME +" SET Name = '"+ employee.getName() +"', " +
                "Password = '"+ employee.getPassword() +"', Email = '"+ employee.getEmail() +"', " +
                "Country = '"+ employee.getCountry() +"' WHERE Id = "+ id;
    }

    public static String getEmployeesQuery() {
        return "SELECT * FROM " + TABLE_NAME;
    }

    public static String getEmployee(int id) {
        return "SELECT * FROM " + TABLE_NAME + " WHERE Id=" + id;
    }

    public static String getIdQuery() {
        return "SELECT Id from " + TABLE_NAME;
    }
}
