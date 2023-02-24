package com.example.vistaraemployeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.vistaraemployeemanager.model.Employee;
import com.example.vistaraemployeemanager.service.EmployeeService;

@Controller
public class EmployeeController {
    
    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/addEmployee")
    public RedirectView add(Employee employee) throws Exception {
        employeeService.add(employee);

        return new RedirectView("add");
    }

    @GetMapping("/add")
    public String showAdd() {
        return "add-employee.jsp";
    }

    @GetMapping("/deleteEmployee")
    public RedirectView delete(@RequestParam int id) throws Exception {
        employeeService.remove(id);

        return new RedirectView("view");
    }

    @GetMapping("/editEmployee")
    public ModelAndView edit(int id) {
        var optEmployee = employeeService.getEmployee(id);
        if (optEmployee.isEmpty())
            return new ModelAndView("view");
        
        var employee = optEmployee.get();
        var mv = new ModelAndView();
        mv.addObject("emp", employee);
        mv.setViewName("edit-employee.jsp");

        return mv;
    }

    @PostMapping("/saveEmployee")
    public RedirectView save(Employee employee) throws Exception {
        employeeService.update(employee.getId(), employee);

        return new RedirectView("view");
    }

    @GetMapping("/view")
    public ModelAndView view(String p, String name) {
        if (name != null && !name.isBlank() && !name.isEmpty()) 
            return filteredView(name);

        var max = 10;
        p = (p == null) ? "1" : p;
        var pInt = Integer.parseInt(p);
        var startFrom = calculateStartFromValue(pInt - 1, max);
        var empList = employeeService.getEmployees(startFrom, max);
        var next = (empList.isEmpty()) ? pInt : (pInt + 1);
        var prev = pInt - 1;

        var mv = new ModelAndView();
        mv.addObject("empList", empList);
        mv.addObject("next", next);
        mv.addObject("prev", prev);
        mv.setViewName("view-employee.jsp");

        return mv;
    }

    private int calculateStartFromValue(int pageInt, int max) {
        pageInt = (pageInt < 0) ? 0 : pageInt;
        return pageInt * max;
    }

    public ModelAndView filteredView(String name) {
        var empList = employeeService.findEmployee(name);
        var mv = new ModelAndView();
        mv.addObject("empList", empList);
        mv.addObject("next", -1);
        mv.setViewName("view-employee.jsp");

        return mv;
    }
}
