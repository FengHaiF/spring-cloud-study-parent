package com.atguigu.spring.cloud.handler;

import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.entity.ResultEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeoutException;

@RestController
public class EmployeeHandler {

    private Logger logger = LoggerFactory.getLogger(EmployeeHandler.class);
    // @HystrixCommand 注解通过fallbackMethod 属性指定断路情况下要调用的备份方法
    @HystrixCommand(fallbackMethod = "getBackupIdEmp")
    @RequestMapping("/provider/get/employee/by/id")
    public Employee getEmployeeRemote(@RequestParam("empId") Integer empId) throws InterruptedException {
        // 获取当前Web 应用的端口号
//        int serverPort = request.getServerPort();
        logger.info("empId = "+empId);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Thread.sleep(5000);

        return new Employee(555, "tom555", 555.55);
    }

    public Employee getBackupIdEmp(Integer empId){
        return new Employee(1,"erroe",1.);
    }

    @HystrixCommand(fallbackMethod = "getBackupEmp")
    @RequestMapping("/provider/get/Circut/by/singal")
    public ResultEntity<Employee> getCircutEmp(@RequestParam("singal") String singal){
        if("break".equals(singal)){
            throw new RuntimeException();
        }
        return ResultEntity.successWithData(new Employee(666, "tom666", 666.6));

    }
    public ResultEntity<Employee> getBackupEmp(@RequestParam("signal") String signal){
        return ResultEntity.failed("circut break out");
    }

}