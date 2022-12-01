package com.example.vistaraemployeemanager;


import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;
import com.example.vistaraemployeemanager.Dao.Datasource;
import com.example.vistaraemployeemanager.Dao.EmployeeDBQueryManager;
import com.example.vistaraemployeemanager.EM.Employee;

public class Main {
    public static void main(String[] args) throws Exception {
        var jdbi = Jdbi.create(Datasource.getDatasource());
        jdbi.registerRowMapper(FieldMapper.factory(Employee.class));
        var query = EmployeeDBQueryManager.getEmployee(4);
        var result = (Optional<Employee>) jdbi.withHandle(handle -> {
            var handleQuery = handle.createQuery(query);
            return handleQuery.mapTo(Employee.class).findFirst();
        });
        System.out.println(result);
    }
}
