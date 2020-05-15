package com.ysh.garbageRecyle.controller;

import com.github.pagehelper.PageInfo;
import com.ysh.garbageRecyle.GetLaws;
import com.ysh.garbageRecyle.entity.NewsEntity;
import com.ysh.garbageRecyle.service.GarbageLawService;
import com.ysh.garbageRecyle.entity.GarbageLawEntity;

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
 * @date 2020-05-08 11:01:43
 */
@Controller
@RequestMapping("/garbageLaw")
public class GarbageLawController {

    @Autowired
    private GarbageLawService service;

    /**
     * 根据Id 查询
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getByPrimaryKey(@PathVariable("id") Integer id) {
		GarbageLawEntity entity = new GarbageLawEntity();
		entity.setLawId(id);
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
    public Object save(@RequestBody GarbageLawEntity entity) {
        return service.save(entity);
    }
    

    /**
     * 通过主键，逻辑删除 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable("id") Integer id) {
		GarbageLawEntity entity = new GarbageLawEntity();
		entity.setLawId(id);
        return service.deleteById(entity);
    }
    

    /**
     * PUT请求，更新数据
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateById(@PathVariable("id") Integer id, @RequestBody GarbageLawEntity entity){
    	 entity.setLawId(id);
         return service.updateById(entity);
    }
    @RequestMapping(value = "/updateLawList",method = RequestMethod.GET)
    @ResponseBody
    public String updateLawList(){
        GetLaws getLaws=new GetLaws();
        List<String> codeList=service.getAllCode();
        List<GarbageLawEntity> list=new ArrayList<>();
        list=getLaws.getLawsList(codeList);
        for(GarbageLawEntity garbageLawEntity:list){
            service.save(garbageLawEntity);
        }
        return "ok";
    }
    @RequestMapping(value = "/toLawsList",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView newsListIndex(Model model,@RequestParam(required = false) Integer pageNum,@RequestParam(required = false) String lawLocation){
        List<String> cityList=service.getAllLawCity();
        model.addAttribute("cityList",cityList);
        Map<String,Object> map=null;
        PageInfo<GarbageLawEntity> pageInfo;
        if(lawLocation!=null&&lawLocation!=""){
            map.put("lawLocation",lawLocation);
        }
        if(pageNum!=null){
            pageInfo=service.queryByPage(pageNum,8,map);
        }else {
            pageInfo=service.queryByPage(1,8,map);
        }
        model.addAttribute("pageInfo",pageInfo);
        return new ModelAndView("lawsList","lawsModel",model);
    }
    @RequestMapping(value = "/queryLawsDetail",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView newsListIndex(@RequestParam Integer lawsId,Model model){
        GarbageLawEntity entity = new GarbageLawEntity();
        entity.setLawId(lawsId);
        entity=service.getByPrimaryKey(entity);
        model.addAttribute("lawsDetail",entity);
        return new ModelAndView("lawsDetail","lawsDetailModel",model);
    }
}

