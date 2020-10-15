package com.atguigu.spring.cloud.api;

import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.entity.ResultEntity;
import com.atguigu.spring.cloud.fallback.MyFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// 在@FeignClient 注解中增加fallbackFactory 属性
// 指定consumer 调用provider 时如果失败所采取的备用方案
// fallbackFactory 指定FallbackFactory 类型的类，保证备用方案返回相同类型的数据
@FeignClient(value = "atguigu-provider",fallbackFactory = MyFallBackFactory.class)
public interface EmployeeRemoteService {

    @RequestMapping("/provider/get/employee/by/id")
    public Employee getEmployeeRemote(@RequestParam("empId") Integer empId);

    @RequestMapping("/provider/get/Circut/by/singal")
    public ResultEntity<Employee> getCircutEmp(@RequestParam("singal") String singal);
}