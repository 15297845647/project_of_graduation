package com.ysh.garbageRecyle.controller;

import com.github.pagehelper.PageInfo;
import com.ysh.garbageRecyle.GetLaws;
import com.ysh.garbageRecyle.GetNews;
import com.ysh.garbageRecyle.entity.GarbageLawEntity;
import com.ysh.garbageRecyle.entity.QuestionEntity;
import com.ysh.garbageRecyle.service.NewsService;
import com.ysh.garbageRecyle.entity.NewsEntity;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 控制层
 * 通过拦截器，或者AOP的方式，处理异常信息
 * Result类，封装返回结果对象 {"code":0,"data":Object,"msg":" 成功/失败"}
 * @author ysh
 * @date 2020-05-08 11:02:22
 */
@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService service;

    /**
     * 根据Id 查询
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getByPrimaryKey(@PathVariable("id") Integer id) {
		NewsEntity entity = new NewsEntity();
		entity.setNewsId(id);
        return service.getByPrimaryKey(entity);
    }
    
    
    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param entity
     */
    @RequestMapping(value = "/queryByPage", method = RequestMethod.POST)
    public Object queryByPage(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "8") Integer pageSize, @RequestBody Map<String, Object> map) {
        return service.queryByPage(pageNo, pageSize, map);
    }
    
    
    /**
     * Post请求，新增数据，成功返回ID
     * @param entity
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object save(@RequestBody NewsEntity entity) {
        return service.save(entity);
    }
    

    /**
     * 通过主键，逻辑删除 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable("id") Integer id) {
		NewsEntity entity = new NewsEntity();
		entity.setNewsId(id);
        return service.deleteById(entity);
    }
    

    /**
     * PUT请求，更新数据
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateById(@PathVariable("id") Integer id, @RequestBody NewsEntity entity){
    	 entity.setNewsId(id);
         return service.updateById(entity);
    }
    //更新新闻资讯
    @RequestMapping(value = "/updateNewsList",method = RequestMethod.GET)
    @ResponseBody
    public int updateNewsList(){
        GetNews getNews=new GetNews();
        List<String> codeList=service.getAllNewsCode();
        List<NewsEntity> list=new ArrayList<>();
        list=getNews.getNewsList(codeList);
        for(NewsEntity newsEntity:list){
            service.save(newsEntity);
        }
        return list.size();
    }

    @RequestMapping(value = "/toNewsList",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView newsListIndex(Model model,@RequestParam(required = false) Integer pageNum,@RequestParam(required = false) String city){
        List<String> cityList=service.getAllCity();
        model.addAttribute("cityList",cityList);
        PageInfo<NewsEntity> pageInfo;
        Map<String,Object> maps=new HashMap<>();
        if(city!=null&&city!=""){
            maps.put("city",city);
        }
        if(pageNum!=null){
            pageInfo=service.queryByPage(pageNum,8,maps);
        }else {
            pageInfo=service.queryByPage(1,8,maps);
        }
        model.addAttribute("pageInfo",pageInfo);
        return new ModelAndView("newsList","newsModel",model);
    }

    @RequestMapping(value = "/queryNewsDetail",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView newsListIndex(@RequestParam Integer newsId,Model model){
        NewsEntity entity = new NewsEntity();
        entity.setNewsId(newsId);
        entity=service.getByPrimaryKey(entity);
        //更新查看次数
        int seeTimes=entity.getSeeTimes();
        int see=seeTimes+1;
        entity.setSeeTimes(see);
        service.updateById(entity);
        model.addAttribute("newsDetail",entity);
        return new ModelAndView("newsDetail","newsDetailModel",model);
    }
    //跳转新闻管理页面
    @RequestMapping(value = "/toNewsManagePage",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toNewsManagePage(Model model){
        List<NewsEntity> newsEntityList=service.selectAllNews();
        model.addAttribute("newsEntityList",newsEntityList);
        return new ModelAndView("newsManage","newsManageModel",model);
    }
    //去新闻页面
    @RequestMapping(value = "/toNewsAddPage",method = RequestMethod.GET)
    public String toNewsAddPage(Model model){
        return "newsAdd";
    }
    //发布新闻
    @RequestMapping(value = "/addNews",method = RequestMethod.POST)
    @ResponseBody
    public String addNews(@RequestBody NewsEntity newsEntity,Model model){
        String content=markdownToHtml(newsEntity.getNewsContentHtml());
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            String s=formatter.format(newsEntity.getNewsPubDate());
            date=formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //设置查看次数
        newsEntity.setSeeTimes(0);
        //设置新闻文本
        newsEntity.setNewsContentHtml(content);
        //设置新闻日期
        newsEntity.setNewsPubDate(date);
        Map<String,Object> map=service.save(newsEntity);
        int id= (int) map.get("id");
        if (id>0){
            return "success";
        }else {
            return "false";
        }
    }

    //删除题目
    @RequestMapping(value = "/deleteNews", method = RequestMethod.POST)
    @ResponseBody
    public String deleteNews(@RequestParam(value = "ids[]") String[] ids,Model model){
        int count=0;
        for (int i=0;i<ids.length;i++){
            int id=Integer.parseInt(ids[i]);
            NewsEntity newsEntity=new NewsEntity();
            newsEntity.setNewsId(id);
            int result= service.deleteById(newsEntity);
            if(result>0){
                count++;
            }
        }
        if (count==ids.length){
            return "success";
        }else {
            return "failed";
        }

    }
    //删除新闻
    //把文本转html格式
    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }
}

