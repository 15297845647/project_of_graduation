package com.ysh.garbageRecyle.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ysh.garbageRecyle.dto.RegisterDto;
import com.ysh.garbageRecyle.entity.GarbageCategoryEntity;
import com.ysh.garbageRecyle.entity.GarbageEntity;
import com.ysh.garbageRecyle.entity.RoleEntity;
import com.ysh.garbageRecyle.service.RoleService;
import com.ysh.garbageRecyle.service.UsersService;
import com.ysh.garbageRecyle.entity.UsersEntity;

import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    @Autowired
    private RoleService roleService;
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
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody UsersEntity entity,Model model) {
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

    @RequestMapping(value ="/toUserManagePage",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toUserManagePage(Model model){
        List<UsersEntity> usersEntityList=service.selectAll();
        model.addAttribute("usersEntityList",usersEntityList);
        List<RoleEntity> roleEntityList=roleService.selectAllRole();
        model.addAttribute("roleEntityList",roleEntityList);
        return new ModelAndView("userManage","userManageModel",model);
    }

    //删除垃圾
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUser(@RequestParam(value = "ids[]") String[] ids,Model model){
        int count=0;
        for (int i=0;i<ids.length;i++){
            int id=Integer.parseInt(ids[i]);
            UsersEntity usersEntity=new UsersEntity();
            usersEntity.setUserId(id);
            int result= service.deleteById(usersEntity);
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
    @RequestMapping(value = "/toEditUser", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toEditUser(@RequestParam("userId") Integer userId,Model model){
        UsersEntity usersEntity=new UsersEntity();
        usersEntity.setUserId(userId);
        usersEntity=service.getByPrimaryKey(usersEntity);
        model.addAttribute("usersEntity",usersEntity);
        List<RoleEntity> roleEntityList=roleService.selectAllRole();
        model.addAttribute("roleEntityList",roleEntityList);
        return new ModelAndView("userEdit","editUserModel",model);
    }

    //修改垃圾
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(@RequestBody UsersEntity usersEntity, Model model){
        int i= service.updateById(usersEntity);
        if (i>0){
            return "success";
        }else {
            return "failed";
        }

    }
    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String tologin(){
        return "login";
    }

    @RequestMapping(value = "/check" ,method = RequestMethod.POST)
    @ResponseBody
    public  UsersEntity check(@RequestBody UsersEntity usersEntity, HttpSession session){
        System.out.println("收到前端ajax请求");
        //向后台数据库查询用户id
        usersEntity=service.userLogin(usersEntity);
        //如果id争取，则传回信息
        if(usersEntity.getUserId()>0){
            session.setAttribute("cur_user",usersEntity);
            System.out.println("传回正确信息");
            return usersEntity;
        }else{
            System.out.println("传回错误信息");
            return usersEntity;
        }
    }

    @RequestMapping(value = "/logout" ,method = RequestMethod.GET)
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute("cur_user");
        session.removeAttribute("testResultDto");
        return "login";
    }
    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    @ResponseBody
    public String register(@RequestBody RegisterDto registerDto,HttpSession session){
        //验证码错误
        if(!registerDto.getCode().equals(session.getAttribute("code"))){
            return "3";
        }
        //判断账户是否已经存在
        List<String> accountNumberList=service.selectAllAccountNumber();
        if(accountNumberList.contains(registerDto.getAccountNumber())){
            return "2";
        }
        //判断手机号是否已经注册了
        List<String> phoneNumberList=service.selectAllPhoneNumber();
        if(phoneNumberList.contains(registerDto.getPhoneNumber())){
            return "4";
        }

        UsersEntity usersEntity=new UsersEntity();
        usersEntity.setAccountNumber(registerDto.getAccountNumber());
        usersEntity.setAddress(registerDto.getAddress());
        usersEntity.setPassword(registerDto.getPassword());
        usersEntity.setRoleId(1);
        usersEntity.setUserName(registerDto.getUserName());
        usersEntity.setPhoneNumber(registerDto.getPhoneNumber());
        Map<String,Object> map= service.save(usersEntity);
        int id= (int) map.get("id");
        if(id>0){
            session.removeAttribute("code");
            return "0";
        }else {
            return "1";
        }
    }
    @RequestMapping(value = "/resertPassword" ,method = RequestMethod.POST)
    @ResponseBody
    public String resertPassword(@RequestBody RegisterDto registerDto,HttpSession session){
        //验证码错误
        if(!registerDto.getCode().equals(session.getAttribute("code"))){
            return "3";
        }
        //判断手机号是否存在
        List<String> phoneNumberList=service.selectAllPhoneNumber();
        if(!phoneNumberList.contains(registerDto.getPhoneNumber())){
            return "4";
        }
        //通过手机号查找用户
        Map<String,Object> map=new HashMap<>();
        map.put("phoneNumber",registerDto.getPhoneNumber());
        PageInfo page=service.queryByPage(1,1,map);
        List<UsersEntity> list=page.getList();
        //系统错误，同一个手机号有多个用户
        if(list.size()!=1){
            return "2";
        }
        UsersEntity usersEntity=list.get(0);
        usersEntity.setPassword(registerDto.getPassword());
        int i=service.updateById(usersEntity);
        if(i>0){
            session.removeAttribute("code");
            return "0";
        }else {
            return "1";
        }
    }

    @ResponseBody
    @GetMapping("/getCode")
    public boolean getCode(@RequestParam("memPhone") String memPhone, HttpSession httpSession){
        String apiUrl = "https://sms_developer.zhenzikj.com";
        String appId = "105816";
        String appSecret = "b9dc352e-6c68-437f-b429-1e924ed10f3d";
        try {
            JSONObject json = null;
            //随机生成验证码
            String code = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);

            //设置发送，模板
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("number", memPhone);
            params.put("templateId", "138");
            String[] templateParams = new String[2];
            templateParams[0] = code;
            templateParams[1] = "5";
            params.put("templateParams", templateParams);
            String result = client.send(params);

            json = JSONObject.parseObject(result);
            if (json.getIntValue("code")!=0){//发送短信失败
                return  false;
            }
            //将验证码存到session中,同时存入创建时间
            //以json存放，这里使用的是阿里的fastjson;
            // 将认证码存入SESSION
            httpSession.setAttribute("code",code);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/toProfile",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toProfile(HttpSession session,Model model){
        UsersEntity usersEntity=new UsersEntity();
        usersEntity= (UsersEntity) session.getAttribute("cur_user");
        model.addAttribute("usersEntity",usersEntity);
        List<RoleEntity> roleEntityList=roleService.selectAllRole();
        model.addAttribute("roleEntityList",roleEntityList);
        return new ModelAndView("profile","editUserModel",model);
    }


}

