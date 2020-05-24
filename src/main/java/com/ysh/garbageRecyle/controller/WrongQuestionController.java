package com.ysh.garbageRecyle.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysh.garbageRecyle.dto.ChooseDto;
import com.ysh.garbageRecyle.dto.QuestionDto;
import com.ysh.garbageRecyle.dto.WrongQuestionDto;
import com.ysh.garbageRecyle.entity.*;
import com.ysh.garbageRecyle.service.GarbageCategoryService;
import com.ysh.garbageRecyle.service.QuestionService;
import com.ysh.garbageRecyle.service.WrongQuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制层
 * 通过拦截器，或者AOP的方式，处理异常信息
 * Result类，封装返回结果对象 {"code":0,"data":Object,"msg":" 成功/失败"}
 * @author ysh
 * @date 2020-05-09 11:36:15
 */
@Controller
@RequestMapping("/wrongQuestion")
public class WrongQuestionController {

    @Autowired
    private WrongQuestionService service;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private GarbageCategoryService garbageCategoryService;
    /**
     * 根据Id 查询
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getByPrimaryKey(@PathVariable("id") Integer id) {
		WrongQuestionEntity entity = new WrongQuestionEntity();
		entity.setWqId(id);
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
    public Object save(@RequestBody WrongQuestionEntity entity) {
        return service.save(entity);
    }
    

    /**
     * 通过主键，逻辑删除 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable("id") Integer id) {
		WrongQuestionEntity entity = new WrongQuestionEntity();
		entity.setWqId(id);
        return service.deleteById(entity);
    }
    

    /**
     * PUT请求，更新数据
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateById(@PathVariable("id") Integer id, @RequestBody WrongQuestionEntity entity){
    	 entity.setWqId(id);
         return service.updateById(entity);
    }

    @RequestMapping(value="/toWrongQuestionPage",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toWrongQuestionPage(Model model, HttpSession session, @RequestParam(required = false) Integer pageNum){
        UsersEntity usersEntity=(UsersEntity)session.getAttribute("cur_user");
        //根据用户id获取用户错题
        //当首页时
        Map<String,Object> map=new HashMap<>();
        map.put("userId",usersEntity.getUserId());
        List<WrongQuestionDto> wrongquestionDtoList=new ArrayList<>();
        PageInfo<WrongQuestionEntity> pageInfo=new PageInfo<>();
        int pageNo;
        if(pageNum!=null){
            //获取错题的id
            pageNo=pageNum;
            pageInfo =service.queryByPage(pageNo,8,map);
         }else {
            pageNo=1;
            pageInfo=service.queryByPage(pageNo,8,map);
        }
        List<WrongQuestionEntity> wrongList=pageInfo.getList();
        //根据错题id查找出题目
            for (WrongQuestionEntity w:wrongList){
            QuestionEntity questionEntity=new QuestionEntity();
            questionEntity.setQuestionId(w.getQuestionId());
            questionEntity=questionService.getByPrimaryKey(questionEntity);
            //转换成Dto
            WrongQuestionDto wrongQuestionDto=new WrongQuestionDto();
                wrongQuestionDto=questionEntityToDto(questionEntity,w);
                wrongquestionDtoList.add(wrongQuestionDto);
            }
            model.addAttribute("navigatepageNums",pageInfo.getNavigatepageNums());
            model.addAttribute("pageNum",pageNo);
            model.addAttribute("questionDtoList",wrongquestionDtoList);
            model.addAttribute("nextPage",pageInfo.getNextPage());
            model.addAttribute("prePage",pageInfo.getPrePage());
        return new ModelAndView("wrongExam","wrongExamModel",model);
    }
    //获取到错题的问题Id查询出题目和错题记录合并成QuestionDto

    //通过WqId找到错题
    @RequestMapping(value = "/getWrongQeustionDto",method = RequestMethod.GET)
    @ResponseBody
    public WrongQuestionDto getWrongQeustionDto(int wqId){
        //通过ID找出错题
        WrongQuestionEntity wrongQuestionEntity=new WrongQuestionEntity();
        wrongQuestionEntity.setWqId(wqId);
        wrongQuestionEntity=service.getByPrimaryKey(wrongQuestionEntity);
        //通过题目ID找出题目详情
        QuestionEntity questionEntity=new QuestionEntity();
        questionEntity.setQuestionId(wrongQuestionEntity.getQuestionId());
        questionEntity=questionService.getByPrimaryKey(questionEntity);
        //转换成DTO
        WrongQuestionDto wrongQuestionDto=new WrongQuestionDto();
        wrongQuestionDto=questionEntityToDto(questionEntity,wrongQuestionEntity);
        return wrongQuestionDto;
    }

    //题目实体和错题实体共同结合成问题实体
    public WrongQuestionDto questionEntityToDto(QuestionEntity entity,WrongQuestionEntity wrongQuestionEntity){
        WrongQuestionDto wrongQuestionDto=new WrongQuestionDto();
        //设置错题Id
        wrongQuestionDto.setWqId(wrongQuestionEntity.getWqId());
        //设置题目
        String title=entity.getQuestionTitle();
        wrongQuestionDto.setQuestionTitle(title);
        //设置选项
        String content=entity.getQuestionContent();
        ChooseDto chooseDto= JSONObject.parseObject(content,ChooseDto.class);
        System.out.println(chooseDto.toString());
        wrongQuestionDto.setChooseDto(chooseDto);
        //设置题目Id
        wrongQuestionDto.setQuestionId(entity.getQuestionId());
        //设置正确答案
        wrongQuestionDto.setRightAnswer(entity.getRightAnswer());
        //设置被答总次数
        wrongQuestionDto.setAnswerTimes(entity.getAnswersTime());
        //设置答错次数
        wrongQuestionDto.setAnsweWrongTimes(entity.getAnswersWrong());
        //设置题目类型
        wrongQuestionDto.setTopicType(entity.getQuestionType());
        //设置题目状态
        wrongQuestionDto.setStatus(entity.getQuestionStatus());
        //设置用户的答案
        wrongQuestionDto.setUsersAnser(wrongQuestionEntity.getUserAnswer());
        //设置垃圾类别id
        wrongQuestionDto.setGabageCategoryId(entity.getQuestionCategory());
        GarbageCategoryEntity garbageCategoryEntity=new GarbageCategoryEntity();
        garbageCategoryEntity.setCategoryId(entity.getQuestionCategory());
        garbageCategoryEntity=garbageCategoryService.getByPrimaryKey(garbageCategoryEntity);
        //设置垃圾类别名称
        wrongQuestionDto.setGarbageCategoryName(garbageCategoryEntity.getCategoryName());
        return wrongQuestionDto;
    }

}

