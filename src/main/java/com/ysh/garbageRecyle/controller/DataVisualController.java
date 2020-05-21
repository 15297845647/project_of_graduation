package com.ysh.garbageRecyle.controller;


import com.ysh.garbageRecyle.dto.*;
import com.ysh.garbageRecyle.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//数据可视化
@Controller
@RequestMapping("/dataVisual")
public class DataVisualController {

    @Autowired
    private GarbageCategoryService garbageCategoryService;
    @Autowired
    private GarbageLawService garbageLawService;
    @Autowired
    private GarbageService garbageService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private WrongQuestionService wrongQuestionService;

    @RequestMapping("/testGetUserCount")
    @ResponseBody
    public List<UserCountDto> testGet(){
        return roleService.userRoleCount();
    }
    @RequestMapping("/testGetQuestionCount")
    @ResponseBody
    public List<QuestionCountDto> testQuestionCount(){
        List<QuestionCountDto> questionCountList=questionService.getQuestionCountDto();
        QuestionCountDto questionCountDto=questionService.getQuestionSumCount();
        for(QuestionCountDto q:questionCountList){
            q.setSumAnswerTimes(questionCountDto.getSumAnswerTimes());
            q.setSumAnswerWrongTimes(questionCountDto.getSumAnswerWrongTimes());
        }
        return questionCountList;
    }
    //统计垃圾类别
    @RequestMapping("/testGetGarbageCategoryCount")
    @ResponseBody
    public List<GarbageCountDto> testGategoryCount(){
        return garbageCategoryService.getGarbageCount();
    }

    @RequestMapping("/testQuestionCategoryCount")
    @ResponseBody
    //统计垃圾类别关联的题目
    public List<QuestionCategoryCountDto> testQuestionCategoryCount(){
        return questionService.getCtegoryQuestionCount();
    }

    //统计新闻数据
    @RequestMapping("/testGetNewsCount")
    @ResponseBody
    public List<NewsCountDto> testNewsCount(){
        return newsService.getNewsCount();
    }

    //统计法律法规数据
    @RequestMapping("/testGetLawsCount")
    @ResponseBody
    public List<LawCountDto> testGetLawsCount(){
        return garbageLawService.getLawCount();
    }
}
