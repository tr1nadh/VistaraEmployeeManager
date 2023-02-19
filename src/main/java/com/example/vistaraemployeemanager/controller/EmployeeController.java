package com.example.vistaraemployeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/deleteEmployee")
    public String delete(@RequestParam int id) throws Exception {
        manager.remove(id);

        return "/view";
    }

    @RequestMapping("/editEmployee")
    public ModelAndView edit(int id) {
        var optEmployee = manager.getEmployee(id);
        if (optEmployee.isEmpty())
            return new ModelAndView("view");
        
        var employee = optEmployee.get();
        var mv = new ModelAndView();
        mv.addObject("emp", employee);
        mv.setViewName("edit-employee.jsp");

        return mv;
    }

    @PostMapping("/saveEmployee")
    public String save(Employee employee) throws Exception {
        manager.update(employee.getId(), employee);

        return "view";
    }
}
