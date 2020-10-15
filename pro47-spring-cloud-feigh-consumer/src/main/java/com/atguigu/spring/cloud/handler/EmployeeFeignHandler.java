package com.atguigu.spring.cloud.handler;

import com.atguigu.spring.cloud.api.EmployeeRemoteService;
import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeFeignHandler {
    @Autowired
    private EmployeeRemoteService employeeRemoteService;

    @RequestMapping("/get/feigh/emp/by/id")
    public Employee getEmplyee(@RequestParam("empId") Integer empId){

        return employeeRemoteService.getEmployeeRemote(empId);
    }
    @RequestMapping("/get/feigh/emp/by/singal")
    public ResultEntity<Employee> getCircutEmplyee(@RequestParam("singal") String singal){

        return employeeRemoteService.getCircutEmp(singal);
    }

}
