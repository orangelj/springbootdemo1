package com.lj.controller;

import com.lj.entity.Employee;
import com.lj.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @RequestMapping("selectAll")
    public String selectAll(HttpServletRequest request)throws Exception{

        List<Employee> employees = employeeService.selectAll();
        request.setAttribute("employees",employees);
        return "emplist";
    }

    @RequestMapping("add")
    public String add(Employee employee)throws Exception{

        employeeService.add(employee);
        return "forward:/employee/selectAll";
    }

    @RequestMapping("delete")
    public String delete(int id)throws Exception{

        employeeService.delete(id);
        return "forward:/employee/selectAll";
    }

    @RequestMapping("update")
    public String update(Employee employee)throws Exception{

        employeeService.update(employee);
        return "forward:/employee/selectAll";
    }
}
