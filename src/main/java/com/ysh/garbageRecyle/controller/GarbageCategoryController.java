package com.ysh.garbageRecyle.controller;

import com.github.pagehelper.PageInfo;
import com.ysh.garbageRecyle.entity.GarbageEntity;
import com.ysh.garbageRecyle.service.GarbageCategoryService;
import com.ysh.garbageRecyle.entity.GarbageCategoryEntity;

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
 * Result类，封装返回结果对象 {"code":0,"data":Object,"msg":" 成功/失败"}
 * @author ysh
 * @date 2020-05-05 10:09:34
 */
@Controller
@RequestMapping("/garbageCategory")
public class GarbageCategoryController {

    @Autowired
    private GarbageCategoryService service;

    /**
     * 根据Id 查询
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getByPrimaryKey(@PathVariable("id") Integer id) {
		GarbageCategoryEntity entity = new GarbageCategoryEntity();
		entity.setCategoryId(id);
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
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    @ResponseBody
    public Object addCategory(@RequestBody GarbageCategoryEntity entity,Model model) {
        entity.setQueryTimes(0);
        Map<String,Object> map=service.save(entity);
        int id= (int) map.get("id");
        return id;
    }
    

    /**
     * 通过主键，逻辑删除 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable("id") Integer id) {
		GarbageCategoryEntity entity = new GarbageCategoryEntity();
		entity.setCategoryId(id);
        return service.deleteById(entity);
    }
    

    /**
     * PUT请求，更新数据
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateById(@PathVariable("id") Integer id, @RequestBody GarbageCategoryEntity entity){
    	 entity.setCategoryId(id);
         return service.updateById(entity);
    }

    //跳转垃圾类别管理页面
    @RequestMapping(value = "/toGarbageCategoryManagePage",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toGarbageCategoryManagePage(Model model){
        List<GarbageCategoryEntity> categoryEntityList=new ArrayList<>();
        categoryEntityList=service.queryByPage(1,10,null).getList();
        model.addAttribute("categoryList",categoryEntityList);
        return new ModelAndView("garbageCategoryManage","garbageCategoryManageModel",model);
    }

    //删除垃圾类别
    @RequestMapping(value = "/deleteGarbageCategory", method = RequestMethod.POST)
    @ResponseBody
    public String deleteGarbageCategory(@RequestParam(value = "ids[]") String[] ids,Model model){
        int count=0;
        for (int i=0;i<ids.length;i++){
            int id=Integer.parseInt(ids[i]);
            GarbageCategoryEntity garbageCategoryEntity=new GarbageCategoryEntity();
            garbageCategoryEntity.setCategoryId(id);
            int result= service.deleteById(garbageCategoryEntity);
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

    //根据id返回一个垃圾
    @RequestMapping(value = "/toEditGarbageCategory", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toEditGarbageCategory(@RequestParam("categoryId") Integer categoryId,Model model){
        GarbageCategoryEntity garbageCategoryEntity=new GarbageCategoryEntity();
        garbageCategoryEntity.setCategoryId(categoryId);
        garbageCategoryEntity=service.getByPrimaryKey(garbageCategoryEntity);
        model.addAttribute("garbageCategoryEntity",garbageCategoryEntity);
        return new ModelAndView("garbageEdit","editGarbageCategoryModel",model);
    }

    //修改垃圾类别
    @RequestMapping(value = "/updateGarbageCategory", method = RequestMethod.POST)
    @ResponseBody
    public String updateGarbageCategory(@RequestBody GarbageCategoryEntity garbageCategoryEntity, Model model){
        int i= service.updateById(garbageCategoryEntity);
        if (i>0){
            return "success";
        }else {
            return "failed";
        }

    }
}

