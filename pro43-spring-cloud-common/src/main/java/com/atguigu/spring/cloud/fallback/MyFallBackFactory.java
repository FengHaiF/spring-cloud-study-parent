package com.atguigu.spring.cloud.fallback;// 请注意自动扫描包的规则


import com.atguigu.spring.cloud.api.EmployeeRemoteService;
import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.entity.ResultEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// 比如：feign-consumer 工程需要使用MyFallBackFactory，那么MyFallBackFactory 应该在
//feign-consumer 工程的主启动类所在包或它的子包下
// 简单来说：哪个工程用这个类，哪个工程必须想办法扫描到这个类
@Component
public class MyFallBackFactory implements FallbackFactory<EmployeeRemoteService> {
    // cause 对象是失败原因对应的异常对象
    @Override
    public EmployeeRemoteService create(Throwable cause) {
        return new EmployeeRemoteService() {


            @Override

            public Employee getEmployeeRemote(Integer empId) {
                return new Employee(444, "call provider failed,fall back here, reason is"
                        +cause.getClass().getName()+" "+cause.getMessage(), 444.444);
            }


            @Override
            public ResultEntity<Employee> getCircutEmp(String singal) {
                return ResultEntity.failed(cause.getMessage());
            }
        };
    }
}

//    @RequestMapping("/provider/save/emp")
//public Employee saveEmp(@RequestBody Employee employee) {
//
//    return null;
//}
//
//@RequestMapping("/provider/get/employee/by/id")
//public Employee getEmployeeById(@RequestParam("empId") Integer empId)
//{
//return null;
//}
////    @RequestMapping("/provider/get/employee/remote")
////    public Employee getEmployeeRemote() {
////
////    return new Employee(444, "call provider failed,fall back here, reason is
////    "+cause.getClass().getName()+" "+cause.getMessage(), 444.444);",11.1);
////
////    }
//}