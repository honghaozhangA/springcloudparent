package com.zhh.springcloud.handler;

import com.zhh.springcloud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HumanResourceHandler {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/getEmployee")
    public Employee getEmployee() {
        // 声明远程微服务provider的主机地址端口号
        // String host = "http://localhost:1000";

        // 将远程微服务调用地址从IP+HOST，改为eureka客户端的微服务（provider）名称
        String host = "http://provider";

        // 声明具体要调用的功能URL
        String url = "/provider/getEmployee";

        // 通过RestTemplate调用远程的微服务
        return restTemplate.getForObject(host + url, Employee.class);
    }
}
