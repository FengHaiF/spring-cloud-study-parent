package com.atguigu.spring.cloud.handler;

import com.atguigu.spring.cloud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HumanResourceHandler {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/get/emp")
    public Employee getEmployee(){
//        String host = "http://localhost:1000";
        String host = "http://atguigu-provider";

        String url = "/provider/get/employee/remote";
        return restTemplate.getForObject(host+url,Employee.class);
    }
}
