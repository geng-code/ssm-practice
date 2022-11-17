package com.zgc.crud.service;

import com.zgc.crud.bean.Employee;

import java.util.List;

/**
 * @author 耿
 * @version 1.0
 * @Date 2022/11/10 16:22
 * description:
 */
public interface EmployeeService {

    List<Employee> getAll();

    void saveEmp(Employee employee);

    boolean checkUser(String empName);

    Employee getEmpById(Integer id);

    void updateEmp(Employee employee);

    void delEmp(Integer id);

    void delBatchEmp(List<Integer> idList);
}
