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

    @RequestMapping("/view")
    public ModelAndView view(String p) {
        var max = 10;
        p = (p == null) ? "1" : p;
        var pInt = Integer.parseInt(p);
        var startFrom = calculateStartFromValue(pInt - 1, max);
        var empList = manager.getEmployees(startFrom, max);
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
}
