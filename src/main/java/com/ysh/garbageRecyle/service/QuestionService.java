package com.ysh.garbageRecyle.service;

import com.ysh.garbageRecyle.dto.QuestionCategoryCountDto;
import com.ysh.garbageRecyle.dto.QuestionCountDto;
import com.ysh.garbageRecyle.dto.QuestionDto;
import com.ysh.garbageRecyle.entity.QuestionEntity;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 服务实现层接口
 * @author yuxue
 * @date 2020-05-09 11:36:07
 */
public interface QuestionService {
	
    public QuestionEntity getByPrimaryKey(QuestionEntity questionEntity);
    
    public PageInfo<QuestionEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map);
    
    public Map<String, Object> save(QuestionEntity questionEntity);

	public Integer deleteById(QuestionEntity questionEntity);

    public Integer updateById(QuestionEntity questionEntity);

    public List<QuestionEntity> getBtRand(int dataNum);

    //查询所有题目
    public List<QuestionEntity> findAll();

    //统计当个题目的情况
    public List<QuestionCountDto> getQuestionCountDto();
    //统计所有的答题总数
    public QuestionCountDto getQuestionSumCount();
    //统计垃圾类别有多少题目
    public List<QuestionCategoryCountDto> getCtegoryQuestionCount();
}
