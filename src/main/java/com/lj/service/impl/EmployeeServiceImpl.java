package com.lj.service.impl;

import com.lj.dao.EmployeeMapper;
import com.lj.entity.Employee;
import com.lj.entity.EmployeeExample;
import com.lj.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Employee> selectAll() {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andIdIsNotNull();
        return employeeMapper.selectByExample(example);
    }

    @Override
    public void delete(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Employee employee) {
        employeeMapper.insert(employee);
    }

    @Override
    public void update(Employee employee) {

        employeeMapper.updateByPrimaryKey(employee);
    }
}
