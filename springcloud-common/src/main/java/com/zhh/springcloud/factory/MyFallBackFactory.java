package com.zhh.springcloud.factory;

import com.zhh.springcloud.api.EmployeeService;
import com.zhh.springcloud.entity.Employee;
import com.zhh.springcloud.util.ResultEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

// 1. 实现consumer端服务降级功能
// 2. 实现FallbackFactory接口时，要传入@FeignClient注解标记的接口类型
// 3. 在create方法中返回@FeignClient注解标记的接口对象，当provider调用失败时，会执行这个对象对应的方法
@Component
public class MyFallBackFactory implements FallbackFactory<EmployeeService> {

    public EmployeeService create(final Throwable throwable) {
        return new EmployeeService() {
            public Employee getEmployee() {
                return null;
            }

            public List<Employee> getEmployeeList(String str) {
                return null;
            }

            public ResultEntity<Employee> getEmpWithTestCallBack(String str) {
                return ResultEntity.failed(throwable.getMessage());
            }
        };
    }
}
