package com.ysh.garbageRecyle.controller;

import com.ysh.garbageRecyle.service.UsersService;
import com.ysh.garbageRecyle.entity.UsersEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 控制层
 * @author ysh
 * @date 2020-05-04 13:56:42
 */
@RequestMapping("/users")
@Controller
public class UsersController {

    @Autowired
    private UsersService service;

    /**
     * 根据Id 查询
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UsersEntity getByPrimaryKey(@PathVariable("id") Integer id) {
        UsersEntity usersEntity=new UsersEntity();
        usersEntity.setUserId(id);
        return service.getByPrimaryKey(usersEntity);
    }
    
    
    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param entity
     */
    @RequestMapping(value = "/queryByPage", method = RequestMethod.POST)
    @ResponseBody
    public Object queryByPage(@RequestParam Integer pageNo, @RequestParam Integer pageSize, @RequestBody Map<String, Object> map) {
        return service.queryByPage(pageNo, pageSize, map);
    }
    
    
    /**
     * Post请求，新增数据，成功返回ID
     * @param entity
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(@RequestBody UsersEntity entity) {
        service.save(entity);
        return "保存成功";
    }
    

    /**
     * 通过主键，逻辑删除 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable("id") Integer id) {
        UsersEntity usersEntity=new UsersEntity();
        usersEntity.setUserId(id);
        service.deleteById(usersEntity);
        return "保存成功";
    }
    

    /**
     * PUT请求，更新数据
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateById(@PathVariable("id") Integer id, @RequestBody UsersEntity entity){
    	 entity.setUserId(id);
        service.updateById(entity);
         return "更新成功";
    }
    

}

