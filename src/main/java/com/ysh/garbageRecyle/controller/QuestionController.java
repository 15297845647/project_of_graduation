package com.ysh.garbageRecyle.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.ysh.garbageRecyle.Decode;
import com.ysh.garbageRecyle.dto.ChooseDto;
import com.ysh.garbageRecyle.dto.QuestionDto;
import com.ysh.garbageRecyle.dto.TestPaper;
import com.ysh.garbageRecyle.entity.GarbageCategoryEntity;
import com.ysh.garbageRecyle.entity.GarbageEntity;
import com.ysh.garbageRecyle.service.GarbageCategoryService;
import com.ysh.garbageRecyle.service.QuestionService;
import com.ysh.garbageRecyle.entity.QuestionEntity;

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
 * @date 2020-05-09 11:36:07
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @Autowired
    private GarbageCategoryService garbageCategoryService;

    /**
     * 根据Id 查询
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object getByPrimaryKey(@PathVariable("id") Integer id) {
		QuestionEntity entity = new QuestionEntity();
		entity.setQuestionId(id);
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
    public Object save(@RequestBody QuestionEntity entity) {
        return service.save(entity);
    }
    

    /**
     * 通过主键，逻辑删除 
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteById(@PathVariable("id") Integer id) {
		QuestionEntity entity = new QuestionEntity();
		entity.setQuestionId(id);
        return service.deleteById(entity);
    }
    

    /**
     * PUT请求，更新数据
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object updateById(@PathVariable("id") Integer id, @RequestBody QuestionEntity entity){
    	 entity.setQuestionId(id);
         return service.updateById(entity);
    }

    /*
    *
    * 试卷初始化
    */
    @RequestMapping(value = "/toTestPaper", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toTestPaper(Model model){
        //设置答题题目数量
        int dataNum=10;
        List<QuestionEntity> questionEntityList=service.getBtRand(dataNum);
        //设置试卷
        TestPaper paper=new TestPaper();
        List<QuestionDto> questionDtoList=new ArrayList<>();
        for (QuestionEntity entity:questionEntityList){
            QuestionDto questionDto=new QuestionDto();
            //设置题目
            String title=entity.getQuestionTitle();
            questionDto.setQuestionTitle(title);
            //设置选项
            String content=entity.getQuestionContent();
            ChooseDto chooseDto=JSONObject.parseObject(content,ChooseDto.class);
            System.out.println(chooseDto.toString());
            questionDto.setChooseDto(chooseDto);
            //设置题目Id
            questionDto.setQuestionId(entity.getQuestionId());
            //设置正确答案
            questionDto.setRightAnswer(entity.getRightAnswer());
            //设置被答总次数
            questionDto.setAnswerTimes(entity.getAnswersTime());
            //设置答错次数
            questionDto.setAnsweWrongTimes(entity.getAnswersWrong());
            //设置题目类型
            questionDto.setTopicType(entity.getQuestionType());
            //设置题目状态
            questionDto.setStatus(entity.getQuestionStatus());
            //设置垃圾类别id
            questionDto.setUsersAnser("");
            questionDto.setGabageCategoryId(entity.getQuestionCategory());
            GarbageCategoryEntity garbageCategoryEntity=new GarbageCategoryEntity();
            garbageCategoryEntity.setCategoryId(entity.getQuestionCategory());
             garbageCategoryEntity=garbageCategoryService.getByPrimaryKey(garbageCategoryEntity);
             //设置垃圾类别名称
             questionDto.setGarbageCategoryName(garbageCategoryEntity.getCategoryName());
            //添加进list中
             questionDtoList.add(questionDto);
        }
        //设置试卷的ID
        paper.setTestPaperId(Decode.getImgFileName());
        //设置试卷的题目
        paper.setQuestionList(questionDtoList);
        //设置题目数量
        paper.setQuestionNumber(questionDtoList.size());
        //设置总分
        paper.setSumScore(100);
        //设置答题时间
        int sumTimes=(questionDtoList.size())*1;
            //小时数
            int hour=sumTimes/60;
            //剩余分钟数
            int minite=sumTimes%60;
            String times="0"+String.valueOf(hour)+":"+String.valueOf(minite);
        paper.setLimitTime(times);
        model.addAttribute("paper",paper);
        return new ModelAndView("exam","examModel",model);
    }
    /**
     * 返回答题结果
     */
    @RequestMapping(value = "/toTestResult", method = RequestMethod.POST)
    @ResponseBody
    public List<QuestionDto> toTestResult(@RequestBody TestPaper paper){
        List<QuestionDto> list=paper.getQuestionList();
        return list;
    }


    //进入题目管理界面
    @RequestMapping(value = "/toManageTopic", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toManageTopic(Model model){
        List<QuestionEntity> list=service.findAll();
        model.addAttribute("topicList",list);
        PageInfo<GarbageCategoryEntity> pageInfo= garbageCategoryService.queryByPage(1,10,null);
        List<GarbageCategoryEntity> categoryList=pageInfo.getList();
        model.addAttribute("categoryList",categoryList);

        return new ModelAndView("topicManage","topicModel",model);
    }
    /*
    *查询所有题目
    */
    @RequestMapping(value = "/findAllTopic", method = RequestMethod.GET)
    public String findAllTopic(Model model){
        List<QuestionEntity> list=service.findAll();
        model.addAttribute("topicList",list);
        return "topicManage::topicList";
    }

    //根据Id查询题目
    @RequestMapping(value = "/toEditQuestion", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getTopic(@RequestParam("topicId") Integer topicId,Model model){
        //获取实体
        QuestionEntity entity=new QuestionEntity();
        entity.setQuestionId(topicId);
        entity=service.getByPrimaryKey(entity);
        //给dto赋值
        QuestionDto questionDto=new QuestionDto();
        //设置题目
        String title=entity.getQuestionTitle();
        questionDto.setQuestionTitle(title);
        //设置选项
        String content=entity.getQuestionContent();
        ChooseDto chooseDto=JSONObject.parseObject(content,ChooseDto.class);
        System.out.println(chooseDto.toString());
        questionDto.setChooseDto(chooseDto);
        //设置题目Id
        questionDto.setQuestionId(entity.getQuestionId());
        //设置正确答案
        questionDto.setRightAnswer(entity.getRightAnswer());
        //设置被答总次数
        questionDto.setAnswerTimes(entity.getAnswersTime());
        //设置答错次数
        questionDto.setAnsweWrongTimes(entity.getAnswersWrong());
        //设置垃圾类别id
        questionDto.setUsersAnser("");
        questionDto.setGabageCategoryId(entity.getQuestionCategory());
        //设置垃圾类别名称
        questionDto.setGarbageCategoryName(entity.getCategoryName());
        //设置题目类型
        questionDto.setTopicType(entity.getQuestionType());
        //设置题目状态
        questionDto.setStatus(entity.getQuestionStatus());
        PageInfo<GarbageCategoryEntity> pageInfo= garbageCategoryService.queryByPage(1,10,null);
        List<GarbageCategoryEntity> categoryList=pageInfo.getList();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("questionDto",questionDto);
        return new ModelAndView("topicEdit","editTopicModel",model);
    }
    //根据Id更新题目
    @RequestMapping(value = "/updateTopic", method = RequestMethod.POST)
    @ResponseBody
    public String updateTopic(@RequestBody QuestionDto questionDto,Model model){
        QuestionEntity questionEntity=new QuestionEntity();
        //设置题目Id
        questionEntity.setQuestionId(questionDto.getQuestionId());
        //设置选项数
        questionEntity.setAnswerNumber(4);
        //设置选项
        ObjectMapper mapper = new ObjectMapper();
        String content="";
        try {
            content=mapper.writeValueAsString(questionDto.getChooseDto());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        questionEntity.setQuestionContent(content);
        //设置题目类型
        questionEntity.setQuestionType(questionDto.getTopicType());
        //设置正确答案
        questionEntity.setRightAnswer(questionDto.getRightAnswer());
        //设置题目问题
        questionEntity.setQuestionTitle(questionDto.getQuestionTitle());
        //设置题目状态
        questionEntity.setQuestionStatus(questionDto.getStatus());
        //设置关联垃圾类别
        questionEntity.setQuestionCategory(questionDto.getGabageCategoryId());
        int i= service.updateById(questionEntity);
        if (i>0){
            return "success";
        }else {
            return "failed";
        }

    }

    //添加题目
    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    @ResponseBody
    public String addTopic(@RequestBody QuestionDto questionDto,Model model){
        QuestionEntity questionEntity=new QuestionEntity();
        //设置题目Id
        questionEntity.setQuestionId(questionDto.getQuestionId());
        //设置选项数
        questionEntity.setAnswerNumber(4);
        //设置选项
        ObjectMapper mapper = new ObjectMapper();
        String content="";
        try {
            content=mapper.writeValueAsString(questionDto.getChooseDto());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        questionEntity.setQuestionContent(content);
        //设置题目类型
        questionEntity.setQuestionType(questionDto.getTopicType());
        //设置正确答案
        questionEntity.setRightAnswer(questionDto.getRightAnswer());
        //设置题目问题
        questionEntity.setQuestionTitle(questionDto.getQuestionTitle());
        //设置题目状态
        questionEntity.setQuestionStatus(questionDto.getStatus());
        //设置答题总次数
        questionEntity.setAnswersTime(0);
        //设置答错次数
        questionEntity.setAnswersWrong(0);
        //设置关联垃圾类别
        questionEntity.setQuestionCategory(questionDto.getGabageCategoryId());
        Map<String,Object> map=service.save(questionEntity);
       int id= (int) map.get("id");
       if(id>0){
           return "success";
       }else {
           return "failed";
       }
    }


    //删除题目
    @RequestMapping(value = "/toEditOnUse", method = RequestMethod.GET)
    @ResponseBody
    public String toEditOnUse(int topicId){
        QuestionEntity entity=new QuestionEntity();
        entity.setQuestionId(topicId);
        entity=service.getByPrimaryKey(entity);
        if(entity.getQuestionStatus()==0){
            entity.setQuestionStatus(1);
        }else {
            entity.setQuestionStatus(0);
        }
        int i= service.updateById(entity);
        if (i>0){
            return "success";
        }else {
            return "failed";
        }
    }

    //删除题目
    @RequestMapping(value = "/deleteTopic", method = RequestMethod.POST)
    @ResponseBody
    public String deleteTopic(@RequestParam(value = "ids[]") String[] ids,Model model){
        int count=0;
        for (int i=0;i<ids.length;i++){
            int id=Integer.parseInt(ids[i]);
            QuestionEntity questionEntity=new QuestionEntity();
            questionEntity.setQuestionId(id);
           int result= service.deleteById(questionEntity);
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

}
