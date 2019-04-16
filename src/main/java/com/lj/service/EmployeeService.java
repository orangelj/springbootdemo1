package com.lj.service;

import com.lj.entity.Employee;

import java.util.List;

public interface EmployeeService {

    //查询所有
    List<Employee> selectAll();
    //删除
    void delete(Integer id);
    //添加
    void add(Employee employee);
    //修改
    void update(Employee employee);

}
