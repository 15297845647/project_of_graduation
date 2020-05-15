package com.ysh.garbageRecyle.controller;

import com.ysh.garbageRecyle.service.QuestionCategoryService;
import com.ysh.garbageRecyle.entity.QuestionCategoryEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

/**
 * 控制层
 * 通过拦截器，或者AOP的方式，处理异常信息
 * Result类，封装返回结果对象 {"code":0,"data":Object,"msg":" 成功/失败"}
 * @author ysh
 * @date 2020-05-09 11:36:10
 */
@Controller
@RequestMapping("/questionCategory")
public class QuestionCategoryController {

    @Autowired
    private QuestionCategoryService service;

    /**
     * 根据Id 查询
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getByPrimaryKey(@PathVariable("id") Integer id) {
		QuestionCategoryEntity entity = new QuestionCategoryEntity();
		entity.setQcId(id);
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
    public Object save(@RequestBody QuestionCategoryEntity entity) {
        return service.save(entity);
    }
    

    /**
     * 通过主键，逻辑删除 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable("id") Integer id) {
		QuestionCategoryEntity entity = new QuestionCategoryEntity();
		entity.setQcId(id);
        return service.deleteById(entity);
    }
    

    /**
     * PUT请求，更新数据
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateById(@PathVariable("id") Integer id, @RequestBody QuestionCategoryEntity entity){
    	 entity.setQcId(id);
         return service.updateById(entity);
    }
    

}

