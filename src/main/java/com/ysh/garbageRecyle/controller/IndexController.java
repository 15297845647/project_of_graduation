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

    @RequestMapping(value = "/toManageIndexLawsList",method = RequestMethod.GET)
    public String toManageIndexLawsList(Model model){
        List<GarbageLawEntity> lawsEntityList=new ArrayList<>();
        PageInfo<GarbageLawEntity> lawsPage=garbageLawService.queryByPage(1,8,null);
        lawsEntityList=lawsPage.getList();
        model.addAttribute("lawsEntityList",lawsEntityList);
        return "adminIndex::lawsShow";
    }

    @RequestMapping(value = "/toManageIndexNewsList",method = RequestMethod.GET)
    public String toManageIndexNewsList(Model model){
        List<NewsEntity> newsEntityList=new ArrayList<>();
        PageInfo<NewsEntity> newsPage=newsService.queryByPage(1,13,null);
        newsEntityList=newsPage.getList();
        model.addAttribute("newsEntityList",newsEntityList);
        return "adminIndex::newsShow";
    }
    @RequestMapping(value = "/toIndexPage")
    public String toIndexPage(Model model){
        return "index";
    }

    @RequestMapping(value = "/toAdminIndexPage")
    public String toAdminIndexPage(Model model){
        return "adminIndex";
    }
    //可视化数据管理啊页面
    @RequestMapping(value = "/toDataVisualManagePage")
    public String toDataVisualManagePage(Model model){
        return "dataVisualManage";
    }
    //可视化数据展示页面
    @RequestMapping(value = "/toDataVisualPage")
    public String toDataVisualPage(Model model){
        return "dataVisual";
    }
}
