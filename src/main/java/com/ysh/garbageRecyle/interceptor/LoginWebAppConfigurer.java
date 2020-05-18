package com.ysh.garbageRecyle.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class LoginWebAppConfigurer  extends WebMvcConfigurerAdapter {

    @Autowired
    LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        List<String> list=new ArrayList<>();
        list.add("/garbage/toEditGarbage");
        list.add("/myRec");
        registry.addInterceptor(loginInterceptor).addPathPatterns(list);
        super.addInterceptors(registry);
    }

}
