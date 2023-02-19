package com.example.vistaraemployeemanager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.example.vistaraemployeemanager.manager", "com.example.vistaraemployeemanager.dao", "com.example.vistaraemployeemanager.controller"})
public class AppConfig {
    
}
