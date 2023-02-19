package com.example.vistaraemployeemanager.controller;

import org.springframework.stereotype.Controller;

import com.example.vistaraemployeemanager.manager.EmployeeManager;

@Controller
public class EmployeeController {
    
    private final EmployeeManager manager;

    public EmployeeController(EmployeeManager manager) {
        this.manager = manager;
    }
}
