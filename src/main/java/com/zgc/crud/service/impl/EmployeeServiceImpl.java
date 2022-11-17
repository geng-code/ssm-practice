package com.zgc.crud.service.impl;

import com.zgc.crud.bean.Employee;
import com.zgc.crud.bean.EmployeeExample;
import com.zgc.crud.dao.EmployeeMapper;
import com.zgc.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 耿
 * @version 1.0
 * @Date 2022/11/10 16:22
 * description:
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getAll() {
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.setOrderByClause("e.emp_id");
        return employeeMapper.selectByExampleWithDept(employeeExample);
    }

    @Override
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    @Override
    public boolean checkUser(String empName) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        int count = employeeMapper.countByExample(example);
        return count == 0;

    }

    @Override
    public Employee getEmpById(Integer id) {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public void delEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void delBatchEmp(List<Integer> idList) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(idList);
        employeeMapper.deleteByExample(example);
    }
}
