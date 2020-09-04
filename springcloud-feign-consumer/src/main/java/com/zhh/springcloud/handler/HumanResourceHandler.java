package com.zhh.springcloud.handler;

import com.zhh.springcloud.api.EmployeeService;
import com.zhh.springcloud.entity.Employee;
import com.zhh.springcloud.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HumanResourceHandler {

    // 装配调用远程微服务的接口，后面像调用本地方法一样直接使用
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/feign/consumer/getEmployee")
    public Employee getEmployee() {
        return employeeService.getEmployee();
    }

    @RequestMapping("/feign/consumer/getEmployeeList")
    public List<Employee> getEmployeeList(String str) {
        return employeeService.getEmployeeList(str);
    }

    @RequestMapping("/feign/consumer/getEmpWithTestCallBack")
    public ResultEntity<Employee> getEmpWithTestCallBack(String str) {
        return employeeService.getEmpWithTestCallBack(str);
    }
}
