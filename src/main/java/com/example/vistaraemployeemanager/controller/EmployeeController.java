package com.example.vistaraemployeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.example.vistaraemployeemanager.manager.EmployeeManager;
import com.example.vistaraemployeemanager.model.Employee;

@Controller
public class EmployeeController {
    
    @Autowired
    private final EmployeeManager manager;

    public EmployeeController(EmployeeManager manager) {
        this.manager = manager;
    }

    @PostMapping("/addEmployee")
    public ModelAndView add(Employee employee) throws Exception {
        manager.add(employee);

        return new ModelAndView("/add");
    }

    @RequestMapping("/add")
    public String showAdd() {
        return "add-employee.jsp";
    }
}
