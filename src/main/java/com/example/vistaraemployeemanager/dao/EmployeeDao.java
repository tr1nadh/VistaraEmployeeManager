package com.example.vistaraemployeemanager.dao;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jdbi.v3.core.Jdbi;

import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.database.JDBIManager;
import com.example.vistaraemployeemanager.database.EmployeeDBQueryManager;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class EmployeeDao {

    private final Jdbi jdbi;
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public EmployeeDao(JDBIManager manager) {
        jdbi = manager.getJdbiConnector();
    }

    public int add(Employee employee) {
        Transaction trans = null;
        try (var session = factory.openSession()) {

            trans = session.getTransaction();
            trans.begin();

            session.persist(employee);

            trans.commit();
            return 0;
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();

            ex.printStackTrace();

            return -1;
        }
    }

    public CompletableFuture<Integer> addAsync(Employee employee) {
        return CompletableFuture.supplyAsync(() -> {
            return add(employee);
        });
    }

    public int remove(int id) {
        Transaction trans = null;
        try (var session = factory.openSession()) {

            trans = session.getTransaction();
            trans.begin();

            var employee = session.find(Employee.class, id);
            session.remove(employee);

            trans.commit();
            return 0;
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();

            ex.printStackTrace();

            return -1;
        }
            
    }

    public CompletableFuture<Integer> removeAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            return remove(id);
        });
    }

    public int update(int id, Employee employee) {
        Transaction trans = null;
        try (var session = factory.openSession()) {

            trans = session.getTransaction();
            trans.begin();

            session.merge(employee);

            trans.commit();
            return 0;
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();

            ex.printStackTrace();

            return -1;
        }
    }

    public CompletableFuture<Integer> updateAsync(int id, Employee employee) {
        return CompletableFuture.supplyAsync(() -> {
            return update(id, employee);
        });
    }

    public ArrayList<Employee> getEmployees() {
        var query = EmployeeDBQueryManager.getEmployeesQuery();
        return (ArrayList<Employee>) jdbi.withHandle(handle -> {
            var handleQuery = handle.createQuery(query);
            return handleQuery.mapTo(Employee.class).list();
        });
    }

    public CompletableFuture<ArrayList<Employee>> getEmployeesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            return getEmployees();
        });
    }

    public Optional<Employee> getEmployee(int id) {
        var query = EmployeeDBQueryManager.getEmployee(id);
        return jdbi.withHandle(handle -> {
            var handleQuery = handle.createQuery(query);
            return handleQuery.mapTo(Employee.class).findFirst();
        });
    }

}
