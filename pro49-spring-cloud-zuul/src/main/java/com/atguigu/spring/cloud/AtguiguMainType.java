package com.atguigu.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

/**
 * Hello world!
 *
 */
//启用zuul代理
@EnableZuulProxy
@SpringBootApplication
public class AtguiguMainType
{
    public static void main( String[] args )
    {

        SpringApplication.run(AtguiguMainType.class);
    }
}
