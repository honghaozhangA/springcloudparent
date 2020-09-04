package com.zhh.springcloud.handler;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhh.springcloud.entity.Employee;
import com.zhh.springcloud.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeHandler {

//    @RequestMapping("/provider/getEmployee")
//    public Employee getEmployee(HttpServletRequest request) {
//                int port = request.getServerPort();
//        return new Employee(111, "employee", 11.11);
//    }

    private Logger logger = LoggerFactory.getLogger(EmployeeHandler.class);

    @RequestMapping("/provider/getEmployee")
    public Employee getEmployee() {
        return new Employee(111, "employee", 11.11);
    }

    @RequestMapping("/provider/getEmployeeList")
    public List<Employee> getEmployeeList(@RequestParam("str") String str){

        logger.info("str======" + str);

        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee(222, "ssss", 22.22));
        list.add(new Employee(333, "ssas", 33.33));
        list.add(new Employee(444, "aawd", 44.44));

        return list;
    }

    @HystrixCommand(fallbackMethod = "getEmpWithTestCallBackFailed")
    @RequestMapping("/provider/getEmpWithTestCallBack")
    public ResultEntity<Employee> getEmpWithTestCallBack(@RequestParam("str") String str) throws InterruptedException {
        if("exception".equals(str)) {
            throw new RuntimeException();
        }

        if ("bang".equals(str)) {
            Thread.sleep(5000);
        }

        return ResultEntity.successWithData(new Employee(55, "5555", 5.21));
    }

    public ResultEntity<Employee> getEmpWithTestCallBackFailed(@RequestParam("str") String str) {
        return ResultEntity.failed("error: HystrixCommand => getEmpWithTestCallBackFailed");
    }
}
