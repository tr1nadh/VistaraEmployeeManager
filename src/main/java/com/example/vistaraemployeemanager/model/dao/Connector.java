package com.example.vistaraemployeemanager.model.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;

import com.example.vistaraemployeemanager.model.em.Employee;

public class Connector {
    public static Jdbi getJdbiConnector() {
        var jdbi = Jdbi.create(Datasource.getDatasource());
        jdbi.registerRowMapper(FieldMapper.factory(Employee.class));
        return jdbi;
    }
}
