package com.example.vistaraemployeemanager;

import com.example.vistaraemployeemanager.model.em.EmployeeManager;

public class Main {
    public static void main(String[] args) throws Exception {
    	var result = EmployeeManager.getAllEmployees();
    	System.out.println(result);
    }
}
