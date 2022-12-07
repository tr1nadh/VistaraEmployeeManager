package com.example.vistaraemployeemanager.dao;

import org.jdbi.v3.core.Jdbi;

import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.database.Connector;
import com.example.vistaraemployeemanager.database.EmployeeDBQueryManager;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class EmployeeDao {

    private static final Jdbi jdbi = Connector.getJdbiConnector();

    public static int add(Employee employee) {
        var query = EmployeeDBQueryManager.getInsertQuery(employee);
        return jdbi.withHandle(handle -> {
            return handle.execute(query);
        });
    }

    public static CompletableFuture<Integer> addAsync(Employee employee) {
        return CompletableFuture.supplyAsync(() -> {
                return add(employee);
        });
    }

    public static int remove(int id) {
        var query = EmployeeDBQueryManager.getDeleteQuery(id);
        return jdbi.withHandle(handle -> {
            var handleQuery = handle.createUpdate(query);
            return handleQuery.execute();
        });
    }

    public static CompletableFuture<Integer> removeAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
                return remove(id);
        });
    }

    public static int update(int id, Employee employee) {
        var query = EmployeeDBQueryManager.getUpdateQuery(id, employee);
        return jdbi.withHandle(handle -> {
            var handleQuery = handle.createUpdate(query);
            return handleQuery.execute();
        });
    }

    public static CompletableFuture<Integer> updateAsync(int id, Employee employee) {
        return CompletableFuture.supplyAsync(() -> {
                return update(id, employee);
        });
    }

    public static ArrayList<Employee> getEmployees() {
        var query = EmployeeDBQueryManager.getEmployeesQuery();
        return (ArrayList<Employee>) jdbi.withHandle(handle -> {
            var handleQuery = handle.createQuery(query);
            return handleQuery.mapTo(Employee.class).list();
        });
    }

    public static CompletableFuture<ArrayList<Employee>> getEmployeesAsync() {
        return CompletableFuture.supplyAsync(EmployeeDao::getEmployees);
    }

    public static Optional<Employee> getEmployee(int id) {
        var query = EmployeeDBQueryManager.getEmployee(id);
        return jdbi.withHandle(handle -> {
            var handleQuery = handle.createQuery(query);
            return handleQuery.mapTo(Employee.class).findFirst();
        });
    }

}
