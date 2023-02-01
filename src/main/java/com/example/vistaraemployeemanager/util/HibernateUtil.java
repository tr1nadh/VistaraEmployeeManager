package com.example.vistaraemployeemanager.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.example.vistaraemployeemanager.model.Employee;

public class HibernateUtil {
    
    public static SessionFactory getSessionFactory() {
        final String hb = "hibernate.";
        final String hk = "hikari.";

        var configure = new Configuration();

        configure.setProperty(hb+"hbm2ddl.auto", "update");
        configure.setProperty(hb+"hbm2ddl.show_sql", "true");
        configure.setProperty(hb+"hbm2ddl.format_sql", "false");
        configure.setProperty(hb+"dialect", "org.hibernate.dialect.MySQLDialect");
        configure.setProperty(hb+"connection.provider_class", "com.zaxxer.hikari.hibernate.HikariConnectionProvider");

        // Data source
        
        configure.setProperty(hb+hk+"dataSourceClassName", "com.mysql.cj.jdbc.MysqlDataSource");
        configure.setProperty(hb+hk+"dataSource.url", "jdbc:mysql://localhost:3306/vemdb");
        configure.setProperty(hb+hk+"dataSource.user", "root");
        configure.setProperty(hb+hk+"dataSource.password", System.getenv("DB_PASSWORD"));
        configure.setProperty(hb+hk+"minimumIdle", "5");
        configure.setProperty(hb+hk+"maximumPoolSize", "10");
        configure.setProperty(hb+hk+"idleTimeout", "30000");
        configure.setProperty(hb+hk+"dataSource.cachePrepStmts", "true");
        configure.setProperty(hb+hk+"dataSource.prepStmtCacheSize", "250");
        configure.setProperty(hb+hk+"dataSource.prepStmtCacheSqlLimit", "2048");
        configure.setProperty(hb+hk+"connectionTimeout", "10000");

        configure.addAnnotatedClass(Employee.class);
        return configure.buildSessionFactory();
    }
}
