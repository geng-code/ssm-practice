package com.zgc.crud.controller;

import com.zgc.crud.bean.Department;
import com.zgc.crud.bean.Msg;
import com.zgc.crud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 耿
 * @version 1.0
 * @Date 2022/11/13 14:11
 * description:
 */
@Controller
public class DeptController {

    @Autowired
    DeptService deptService;

    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts() {
        //查询
        List<Department> depts = deptService.getDepts();
        System.out.println("查询");
        return Msg.success().add("depts",depts);
    }
}
