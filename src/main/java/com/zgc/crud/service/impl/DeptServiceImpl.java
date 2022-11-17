package com.zgc.crud.service.impl;

import com.zgc.crud.bean.Department;
import com.zgc.crud.dao.DepartmentMapper;
import com.zgc.crud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 耿
 * @version 1.0
 * @Date 2022/11/13 14:15
 * description:
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DepartmentMapper mapper;

    @Override
    public List<Department> getDepts() {
        return mapper.selectByExample(null);
    }
}
