package com.example.vistaraemployeemanager.database;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;
import com.example.vistaraemployeemanager.model.Employee;


public class JDBIManager {

    private final IDatasource datasource;

    public JDBIManager(IDatasource datasource) {
        this.datasource = datasource;
    }

    public Jdbi getJdbiConnector() {
        var jdbi = Jdbi.create(datasource.getDatasource());
        jdbi.registerRowMapper(FieldMapper.factory(Employee.class));
        return jdbi;
    }
}
