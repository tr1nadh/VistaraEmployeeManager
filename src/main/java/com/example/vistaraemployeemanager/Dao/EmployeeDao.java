package com.example.vistaraemployeemanager.Dao;

import com.example.vistaraemployeemanager.EM.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.jdbi.v3.core.Jdbi;

public class EmployeeDao {

    private static final String url = "jdbc:derby:/mnt/DRIVE/Programming/Projects/VistaraEmployeeManager/src/main/resources/VistaraEmployeeManagerDB;";

    private static Jdbi jdbi = Connector.getJdbiConnector();

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public static int add(Employee employee) throws SQLException {
        var query = EmployeeDBQueryManager.getInsertQuery(employee);
        return jdbi.withHandle(handle -> {
            return handle.execute(query);
        });
    }

    public static CompletableFuture<Integer> addAsync(Employee employee) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return add(employee);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static int remove(int id) throws SQLException {
        var query = EmployeeDBQueryManager.getDeleteQuery(id);
        return jdbi.withHandle(handle -> {
            var handleQuery = handle.createUpdate(query);
            return handleQuery.execute();
        });
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
        var query = EmployeeDBQueryManager.getUpdateQuery(id, employee);
        return jdbi.withHandle(handle -> {
            var handleQuery = handle.createUpdate(query);
            return handleQuery.execute();
        });
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
        return (ArrayList<Employee>) jdbi.withHandle(handle -> {
            var handleQuery = handle.createQuery(query);
            return handleQuery.mapTo(Employee.class).list();
        });
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

    public static Optional<Employee> getEmployee(int id) throws SQLException {
        var query = EmployeeDBQueryManager.getEmployee(id);
        return (Optional<Employee>) jdbi.withHandle(handle -> {
            var handleQuery = handle.createQuery(query);
            return handleQuery.mapTo(Employee.class).findFirst();
        });
    }

}
