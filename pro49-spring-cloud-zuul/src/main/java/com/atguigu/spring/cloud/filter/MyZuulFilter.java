package com.atguigu.spring.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class MyZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 1.获取当前RequestContext 对象
        RequestContext context = RequestContext.getCurrentContext();
// 2.获取当前请求对象
        HttpServletRequest request = context.getRequest();
// 3.获取当前请求要访问的目标地址
        String servletPath = request.getServletPath();
// 4.打印
        System.err.println("servletPath="+servletPath);
// 5.当前方法返回值
// true：表示应该过滤，下面继续执行run()方法
// false：表示不过滤，直接放行
        return true;
//        return false;
    }

    @Override
    public Object run() throws ZuulException {
        // 执行具体过滤逻辑
        System.err.println("run() ...");
// 官方文档说：当前实现会忽略这个返回值，所以返回null 即可
        return null;
    }
}
