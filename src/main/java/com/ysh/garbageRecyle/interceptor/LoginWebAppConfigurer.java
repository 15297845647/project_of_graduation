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
        list.add("/garbageCategory/addCategory");
        list.add("/garbageCategory/toGarbageCategoryManagePage");
        list.add("/garbageCategory/deleteGarbageCategory");
        list.add("/garbageCategory/toEditGarbageCategory");
        list.add("/garbageCategory/updateGarbageCategory");
        list.add("/garbage/toGarbageManagePage");
        list.add("/garbage/deleteGarbage");
        list.add("/garbage/addGarbage");
        list.add("/garbage/updateGarbage");
        list.add("/garbage/toEditGarbage");
        list.add("/garbageLaw/toLawsManagePage");
        list.add("/garbageLaw/deleteLaws");
        list.add("/index/toManageIndexLawsList");
        list.add("/index/toManageIndexNewsList");
        list.add("/index/toAdminIndexPage");
        list.add("/index/toDataVisualManagePage");
        list.add("/news/toNewsManagePage");
        list.add("/news/toNewsAddPage");
        list.add("/news/addNews");
        list.add("/news/deleteNews");
        list.add("/question/toTestPaper");
        list.add("/question/toTestResult");
        list.add("/question/toResultPage");
        list.add("/question/toManageTopic");
        list.add("/question/toEditQuestion");
        list.add("/question/updateTopic");
        list.add("/question/addTopic");
        list.add("/question/toEditOnUse");
        list.add("/question/deleteTopic");
        list.add("/role/toRoleManagePage");
        list.add("/role/deleteRole");
        list.add("/role/toEditRole");
        list.add("/role/updateRole");
        list.add("/users/toUserManagePage");
        list.add("/users/deleteUser");
        list.add("/users/toEditUser");
        list.add("/users/updateUser");
        list.add("/wrongQuestion/toWrongQuestionPage");
        list.add("/wrongQuestion/getWrongQeustionDto");
        registry.addInterceptor(loginInterceptor).addPathPatterns(list);
        super.addInterceptors(registry);
    }

}
