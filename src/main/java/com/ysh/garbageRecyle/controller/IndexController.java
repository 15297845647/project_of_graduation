package com.ysh.garbageRecyle.controller;


import com.github.pagehelper.PageInfo;
import com.ysh.garbageRecyle.entity.GarbageLawEntity;
import com.ysh.garbageRecyle.entity.NewsEntity;
import com.ysh.garbageRecyle.service.GarbageLawService;
import com.ysh.garbageRecyle.service.GarbageService;
import com.ysh.garbageRecyle.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private GarbageService garbageService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private GarbageLawService garbageLawService;

    @RequestMapping(value = "/toIndexNewsList",method = RequestMethod.GET)
    public String toIndexNewsList(Model model){
        List<NewsEntity> newsEntityList=new ArrayList<>();
        PageInfo<NewsEntity> newsPage=newsService.queryByPage(1,13,null);
        newsEntityList=newsPage.getList();
        model.addAttribute("newsEntityList",newsEntityList);
        return "index::newsShow";
    }

    @RequestMapping(value = "/toIndexLawsList",method = RequestMethod.GET)
    public String toIndexLawsList(Model model){
        List<GarbageLawEntity> lawsEntityList=new ArrayList<>();
        PageInfo<GarbageLawEntity> lawsPage=garbageLawService.queryByPage(1,8,null);
        lawsEntityList=lawsPage.getList();
        model.addAttribute("lawsEntityList",lawsEntityList);
        return "index::lawsShow";
    }
    @RequestMapping(value = "/toIndexPage")
    public String toIndexPage(Model model){
        return "index";
    }
}
