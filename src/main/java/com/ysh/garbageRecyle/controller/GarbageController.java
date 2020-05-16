package com.ysh.garbageRecyle.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.ysh.garbageRecyle.dto.QuestionDto;
import com.ysh.garbageRecyle.entity.GarbageCategoryEntity;
import com.ysh.garbageRecyle.entity.GarbageLawEntity;
import com.ysh.garbageRecyle.entity.QuestionEntity;
import com.ysh.garbageRecyle.service.GarbageCategoryService;
import com.ysh.garbageRecyle.service.GarbageService;
import com.ysh.garbageRecyle.entity.GarbageEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 控制层
 * 通过拦截器，或者AOP的方式，处理异常信息
 * @author ysh
 * @date 2020-05-05 10:10:02
 */
@Controller
@RequestMapping("/garbage")
public class GarbageController {

    @Autowired
    private GarbageService service;

    @Autowired
    private GarbageCategoryService garbageCategoryService;

    /**
     * 根据Id 查询
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getByPrimaryKey(@PathVariable("id") Integer id) {
		GarbageEntity entity = new GarbageEntity();
		entity.setGarbageId(id);
        return service.getByPrimaryKey(entity);
    }
    
    
    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param entity
     */
    @RequestMapping(value = "/queryByPage", method = RequestMethod.POST)
    public Object queryByPage(@RequestParam Integer pageNo, @RequestParam Integer pageSize, @RequestBody Map<String, Object> map) {
        return service.queryByPage(pageNo, pageSize, map);
    }
    
    
    /**
     * Post请求，新增数据，成功返回ID
     * @param entity
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object save(@RequestBody GarbageEntity entity) {
        return service.save(entity);
    }
    

    /**
     * 通过主键，逻辑删除 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable("id") Integer id) {
		GarbageEntity entity = new GarbageEntity();
		entity.setGarbageId(id);
        return service.deleteById(entity);
    }
    

    /**
     * PUT请求，更新数据
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateById(@PathVariable("id") Integer id, @RequestBody GarbageEntity entity){
    	 entity.setGarbageId(id);
         return service.updateById(entity);
    }
    @RequestMapping(value = "/toQueryGarbage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getTopGarbage(Model model){
        List<GarbageEntity> list=new ArrayList<>();
        list=service.getTopGarbage();
        model.addAttribute("garbageTopList",list);
        String garbageName="";
        model.addAttribute("queryGarbage",garbageName);
        model.addAttribute("queryList",null);
        return new ModelAndView("queryGarbage","garbageModel",model);
    }
    @RequestMapping(value = "/garbageQuery", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView garbageQuery(@RequestParam(required = false) String garbageName,@RequestParam(required = false) String garbageId,Model model){
       if(garbageName!=""&&garbageName!=null){
           GarbageEntity entity=new GarbageEntity();
           entity.setGarbageName(garbageName);
           List<GarbageEntity> querylist=service.findGarbageByName(entity);
           model.addAttribute("queryList",querylist);
       }
        List<GarbageEntity> list=new ArrayList<>();
        list=service.getTopGarbage();
        model.addAttribute("queryGarbage",garbageName);
        model.addAttribute("garbageTopList",list);
        return new ModelAndView("queryGarbage","garbageModel",model);
    }
    /*
    * 根据垃圾名称精确查找
    */
    @RequestMapping(value = "/queryGarbageByName", method = RequestMethod.GET,produces = "text/html;charset=utf-8")
    @ResponseBody
    public String queryGarbageByName(@RequestParam(required = false) String garbageName){
        String result="";
        GarbageEntity entity=new GarbageEntity();
        if(garbageName!=""&&garbageName!=null){
            entity=service.findByEqualName(garbageName);
        }
        if(entity==null){
            result="The name not exit in system";
        }else {
           String categoryName=entity.getCategoryName();
           result=garbageName+"   :   "+categoryName;
        }
        System.out.println(result);
        return result;
    }

    //跳转垃圾管理页面
    @RequestMapping(value = "/toGarbageManagePage",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toGarbageManagePage(Model model,@RequestParam(required = false) Integer categoryCode){
        List<GarbageEntity> garbageEntityList=new ArrayList<>();
        if(categoryCode==null){
            GarbageEntity garbageEntity=new GarbageEntity();
            garbageEntity.setGarbageCategoryCode(1);
            garbageEntityList=service.selectByGarbageCategotyCode(garbageEntity);
            model.addAttribute("categorySet",1);
        }else {
            GarbageEntity garbageEntity=new GarbageEntity();
            int code=categoryCode.intValue();
            garbageEntity.setGarbageCategoryCode(code);
            garbageEntityList=service.selectByGarbageCategotyCode(garbageEntity);
            model.addAttribute("categorySet",categoryCode);
        }
        model.addAttribute("categorySet",categoryCode);
        model.addAttribute("garbageEntityList",garbageEntityList);
        PageInfo<GarbageCategoryEntity> pageInfo= garbageCategoryService.queryByPage(1,10,null);
        List<GarbageCategoryEntity> categoryList=pageInfo.getList();
        model.addAttribute("categoryList",categoryList);
        return new ModelAndView("garbageManage","garbageManageModel",model);
    }


    //删除垃圾
    @RequestMapping(value = "/deleteGarbage", method = RequestMethod.POST)
    @ResponseBody
    public String deleteGarbage(@RequestParam(value = "ids[]") String[] ids,Model model){
        int count=0;
        for (int i=0;i<ids.length;i++){
            int id=Integer.parseInt(ids[i]);
            GarbageEntity garbageEntity=new GarbageEntity();
            garbageEntity.setGarbageId(id);
            int result= service.deleteById(garbageEntity);
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

    //添加垃圾
    @RequestMapping(value = "/addGarbage", method = RequestMethod.POST)
    @ResponseBody
    public String addGarbage(@RequestBody GarbageEntity garbageEntity, Model model){

        garbageEntity.setQueryTimes(0);
        Map<String,Object> map=service.save(garbageEntity);
        int id= (int) map.get("id");
        if(id>0){
            return "success";
        }else {
            return "failed";
        }
    }

    //修改垃圾
    @RequestMapping(value = "/updateGarbage", method = RequestMethod.POST)
    @ResponseBody
    public String updateGarbage(@RequestBody GarbageEntity garbageEntity, Model model){
       int i= service.updateById(garbageEntity);
        if (i>0){
            return "success";
        }else {
            return "failed";
        }

    }

    //根据id返回一个垃圾
    @RequestMapping(value = "/toEditGarbage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toEditGarbage(@RequestParam("garbageId") Integer garbageId,Model model){
        GarbageEntity garbageEntity=new GarbageEntity();
        garbageEntity.setGarbageId(garbageId);
        garbageEntity=service.getByPrimaryKey(garbageEntity);
        model.addAttribute("garbageEntity",garbageEntity);
        PageInfo<GarbageCategoryEntity> pageInfo= garbageCategoryService.queryByPage(1,10,null);
        List<GarbageCategoryEntity> categoryList=pageInfo.getList();
        model.addAttribute("categoryList",categoryList);
        return new ModelAndView("garbageEdit","editGarbageModel",model);
    }

}

