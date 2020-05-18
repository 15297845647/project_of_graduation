package com.ysh.garbageRecyle.controller;

import com.ysh.garbageRecyle.entity.UsersEntity;
import com.ysh.garbageRecyle.service.RoleService;
import com.ysh.garbageRecyle.entity.RoleEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Map;

/**
 * 控制层
 * 通过拦截器，或者AOP的方式，处理异常信息
 * Result类，封装返回结果对象 {"code":0,"data":Object,"msg":" 成功/失败"}
 * @author ysh
 * @date 2020-05-05 00:05:20
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    /**
     * 根据Id 查询
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getByPrimaryKey(@PathVariable("id") Integer id) {
		RoleEntity entity = new RoleEntity();
		entity.setRoleId(id);
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
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody RoleEntity entity,Model model) {
        Map<String,Object> map= service.save(entity);
        int id= (int) map.get("id");
        if(id>0){
            return "success";
        }else {
            return "failed";
        }
    }
    

    /**
     * 通过主键，逻辑删除 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable("id") Integer id) {
		RoleEntity entity = new RoleEntity();
		entity.setRoleId(id);
        return service.deleteById(entity);
    }
    

    /**
     * PUT请求，更新数据
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateById(@PathVariable("id") Integer id, @RequestBody RoleEntity entity){
    	 entity.setRoleId(id);
         return service.updateById(entity);
    }

    @RequestMapping(value ="/toRoleManagePage",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toRoleManagePage(Model model){
        List<RoleEntity> roleEntityList=service.selectAllRole();
        model.addAttribute("roleEntityList",roleEntityList);
        return new ModelAndView("roleManage","roleManageModel",model);
    }

    //删除垃圾
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @ResponseBody
    public String deleteRole(@RequestParam(value = "ids[]") String[] ids,Model model){
        int count=0;
        for (int i=0;i<ids.length;i++){
            int id=Integer.parseInt(ids[i]);
            RoleEntity roleEntity=new RoleEntity();
            roleEntity.setRoleId(id);
            int result= service.deleteById(roleEntity);
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
    //根据id跳转至编辑用户界面
    @RequestMapping(value = "/toEditRole", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toEditRole(@RequestParam("roleId") Integer roleId,Model model){
        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setRoleId(roleId);
        roleEntity=service.getByPrimaryKey(roleEntity);
        model.addAttribute("roleEntity",roleEntity);
        return new ModelAndView("roleEdit","editRoleModel",model);
    }


    //修改角色信息
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    @ResponseBody
    public String updateRole(@RequestBody RoleEntity roleEntity, Model model){
        int i= service.updateById(roleEntity);
        if (i>0){
            return "success";
        }else {
            return "failed";
        }

    }

}

