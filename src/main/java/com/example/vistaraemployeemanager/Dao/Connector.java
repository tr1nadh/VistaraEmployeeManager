package com.example.vistaraemployeemanager.Dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;

import com.example.vistaraemployeemanager.EM.Employee;

public class Connector {
    public static Jdbi getJdbiConnector() {
        var jdbi = Jdbi.create(Datasource.getDatasource());
        jdbi.registerRowMapper(FieldMapper.factory(Employee.class));
        return jdbi;
    }
}
