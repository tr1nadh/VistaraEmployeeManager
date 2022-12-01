package com.example.vistaraemployeemanager.Dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;

import com.example.vistaraemployeemanager.EM.Employee;

public class Database {
    public static Jdbi getJdbi() {
        var jdbi = Jdbi.create(Datasource.getDatasource());
        jdbi.registerRowMapper(FieldMapper.factory(Employee.class));
        return jdbi;
    }
}
