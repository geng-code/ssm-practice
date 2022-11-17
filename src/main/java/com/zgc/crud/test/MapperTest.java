package com.zgc.crud.test;

import com.zgc.crud.bean.Department;
import com.zgc.crud.bean.Employee;
import com.zgc.crud.dao.DepartmentMapper;
import com.zgc.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @author 耿
 * @version 1.0
 * @Date 2022/11/9 20:28
 * description:
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class MapperTest {


    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD(){

        System.out.println(departmentMapper);
        //部门插入
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));
        //员工插入
//        employeeMapper.insertSelective(new Employee(null,"Jerry","男", "Jerry@spring.com", 1));
        //批量插入
//        for(){
//            employeeMapper.insertSelective(new Employee(null,"Jerry","男", "Jerry@spring.com", 1));
//        }
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        for (int i = 0; i < 1000; i++) {
            String name = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null,name,"男", name+"@spring.com", 1));
        }

    }
}
