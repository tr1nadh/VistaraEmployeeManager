package com.example.vistaraemployeemanager;

import com.example.vistaraemployeemanager.EmployeeManager.EmployeeManager;
import com.example.vistaraemployeemanager.EmployeeManager.Database.EmployeeDB;

public class Main {
    public static void main(String[] args) throws Exception {
    	var list = EmployeeManager.getAllEmployees();
    	for (var emp : list) {
    		System.out.println(emp);
    	}
    }
}
