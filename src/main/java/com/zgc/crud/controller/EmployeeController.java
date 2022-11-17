package com.zgc.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgc.crud.bean.Employee;
import com.zgc.crud.bean.Msg;
import com.zgc.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 耿
 * @version 1.0
 * @Date 2022/11/9 21:03
 * description:
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum, Model model) {
        //开启分页
        PageHelper.startPage(pageNum, 5);
        //查询
        List<Employee> emps = employeeService.getAll();
        //pageInfo包装
        PageInfo<Employee> pageInfo = new PageInfo<>(emps, 5);

        return Msg.success().add("pageInfo", pageInfo);
    }


    /**
     * 员工保存
     * 1、支持JSR303校验
     * 2、导入Hibernate validator 包
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {

        Map<String, Object> map = new HashMap<>();

        if(result.hasErrors()){
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Msg.fail();
        } else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }

    }

    /**
     * 根据id返回指定修改的用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmpById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmpById(id);
        return Msg.success().add("emp", employee);
    }


    /**
     * 更新员工
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee) {

        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * 检验用户名是否可用
     * @param empName
     * @return
     */
    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@RequestParam("empName") String empName) {

        //先判断用户名是否是合法的表达式
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        boolean matches = empName.matches(regx);
        if(!matches){
            return Msg.fail().add("va_msg","用户名可以是2-5位中文或者6-16位英文和数字的组合");
        }

        boolean flag = employeeService.checkUser(empName);

        if (flag == true) {
            return Msg.success();
        } else {
            return Msg.fail().add("va_msg", "用户名已存在");
        }

    }

    /**
     * 删除单个用户
     * @param ids
     * @return
     */
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg delEmp(@PathVariable String ids){

        System.out.println("id = " + ids);
        if(ids.contains("-")){
            String[] idStr = ids.split("-");
            List<Integer> idList = new ArrayList<>();
            for (String s : idStr) {
                idList.add(Integer.parseInt(s));
            }
            employeeService.delBatchEmp(idList);
        } else {
            employeeService.delEmp(Integer.valueOf(ids));
        }

        return Msg.success();
    }


//    @RequestMapping("/emps")
//    public String getAllEmp(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum, Model model){
//        //开启分页
//        PageHelper.startPage(pageNum, 5);
//        //查询
//        List<Employee> emps = employeeService.getAll();
//        //pageInfo包装
//        PageInfo<Employee> pageInfo = new PageInfo<>(emps,5);
//        //保存到请求作用域中
//        model.addAttribute("pageInfo", pageInfo);
//        return "list";
//    }
}
