package com.zhh.springcloud.api;

import com.zhh.springcloud.entity.Employee;
import com.zhh.springcloud.factory.MyFallBackFactory;
import com.zhh.springcloud.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 表示当前接口和一个provider对应，指要调用的provider微服务名称
// fallbackFactory属性指，provider不可用时，提供备用方案的工厂类型
@FeignClient(value = "provider",fallbackFactory = MyFallBackFactory.class)
public interface EmployeeService {

    // 远程调用的接口方法，
    // 要求@RequestMapping映射的地址一致
    // 要求声明的方法getEmployee一致
    // 要求获取请求参数的@RequestParam、@PathVariable、@RequestBody一致
    @RequestMapping("/provider/getEmployee")
    public Employee getEmployee();

    @RequestMapping("/provider/getEmployeeList")
    public List<Employee> getEmployeeList(@RequestParam("str") String str);

    @RequestMapping("/provider/getEmpWithTestCallBack")
    public ResultEntity<Employee> getEmpWithTestCallBack(@RequestParam("str") String str);
}
