package com.example.vistaraemployeemanager.dao;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.util.HibernateUtil;

@Repository
public class EmployeeDao {

    private final SessionFactory factory = HibernateUtil.getSessionFactory();

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

            var employee = session.get(Employee.class, id);
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
        Transaction trans = null;
        try (var session = factory.openSession()) {

            trans = session.getTransaction();
            trans.begin();

            var empResultset = session.createNativeQuery("select * from employee", Employee.class);

            trans.commit();

            return (ArrayList<Employee>) empResultset.list();
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();

            ex.printStackTrace();

            return null;
        }
    }

    public CompletableFuture<ArrayList<Employee>> getEmployeesAsync() {
        return CompletableFuture.supplyAsync(() -> {
            return getEmployees();
        });
    }

    public Optional<Employee> getEmployee(int id) {
        Employee employee = null;
        Transaction trans = null;
        try (var session = factory.openSession()) {

            trans = session.getTransaction();
            trans.begin();

            employee = session.get(Employee.class, id);

            trans.commit();
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();

            ex.printStackTrace();
        }

        Optional<Employee> optEmp = Optional.ofNullable(employee);
        return optEmp;
    }

    public CompletableFuture<Optional<Employee>> getEmployeeAsync(int id) {
        return CompletableFuture.supplyAsync(() -> {
            return getEmployee(id);
        });
    }

    public ArrayList<Employee> findEmployee(String name) {
        Transaction trans = null;
        try (var session = factory.openSession()) {

            trans = session.getTransaction();
            trans.begin();

            var empResultset = session.createNativeQuery("SELECT * FROM employee WHERE name REGEXP '" + name + "'", Employee.class);

            trans.commit();

            return (ArrayList<Employee>) empResultset.list();
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();

            ex.printStackTrace();

            return null;
        }
    }

    
    public ArrayList<Employee> getEmployees(int startFrom, int max) {
        Transaction trans = null;
        try (var session = factory.openSession()) {

            trans = session.getTransaction();
            trans.begin();

            var empResultset = session.createNativeQuery("SELECT * FROM employee LIMIT " + startFrom + ", " + max, Employee.class);

            trans.commit();

            return (ArrayList<Employee>) empResultset.list();
        } catch (Exception ex) {
            if (trans != null)
                trans.rollback();

            ex.printStackTrace();

            return null;
        }
    }
}
