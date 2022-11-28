package com.example.vistaraemployeemanager.EmployeeManager.Database;

import com.example.vistaraemployeemanager.EmployeeManager.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class EmployeeDB {

    private static final String url = "jdbc:derby:/mnt/DRIVE/Programming/Projects/VistaraEmployeeManager/src/main/resources/VistaraEmployeeManagerDB;";


    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public static boolean add(Employee employee) throws SQLException {
        try (var conn = getConnection();
             var stmt = conn.createStatement()) {
            var query = EmployeeDBQueryManager.getInsertQuery(employee);
            return stmt.execute(query);
        }
    }

    public static CompletableFuture<Boolean> addAsync(Employee employee) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return add(employee);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int remove(int id) throws SQLException {
        try (var conn = getConnection();
             var stmt = conn.createStatement()) {
            var query = EmployeeDBQueryManager.getDeleteQuery(id);
            return stmt.executeUpdate(query);
        }
    }

    public static CompletableFuture<Integer> removeAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return remove(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int update(int id, Employee employee) throws SQLException {
        try (var conn = getConnection();
             var stmt = conn.createStatement()) {
            var query = EmployeeDBQueryManager.getUpdateQuery(id, employee);
            return stmt.executeUpdate(query);
        }
    }

    public static CompletableFuture<Integer> updateAsync(int id, Employee employee) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return update(id, employee);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static ArrayList<Employee> getEmployees() throws SQLException {
        var query = EmployeeDBQueryManager.getEmployeesQuery();
        try (var conn = getConnection();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(query)) {
            var empList = new ArrayList<Employee>();
            while (rs.next()) {
                var emp = new Employee();
                emp.setId(rs.getInt("Id"));
                emp.setName(rs.getString("Name"));
                emp.setPassword(rs.getString("Password"));
                emp.setEmail(rs.getString("Email"));
                emp.setCountry(rs.getString("Country"));
                empList.add(emp);
            }
            return empList;
        }
    }

    public static CompletableFuture<ArrayList<Employee>> getEmployeesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getEmployees();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static Employee getEmployee(int id) throws SQLException {
        var query = EmployeeDBQueryManager.getEmployee(id);
        try (var conn = getConnection();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(query);) {

            var emp = new Employee();
            while (rs.next()) {
                emp.setId(rs.getInt("Id"));
                emp.setName(rs.getString("Name"));
                emp.setPassword(rs.getString("Password"));
                emp.setEmail(rs.getString("Email"));
                emp.setCountry(rs.getString("Country"));
            }

            return emp;
        }
    }

    public static ArrayList<Integer> getAllottedIDs() throws SQLException {
        var query = EmployeeDBQueryManager.getIdQuery();
        try (var conn = getConnection();
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(query);
             ) {

            var list = new ArrayList<Integer>();
            while (rs.next()) list.add(rs.getInt("Id"));

            return list;
        }
    }

    public static CompletableFuture<ArrayList<Integer>> getAllottedIDsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getAllottedIDs();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
