package com.ysh.garbageRecyle.service;

import com.ysh.garbageRecyle.entity.QuestionCategoryEntity;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * 服务实现层接口
 * @author yuxue
 * @date 2020-05-09 11:36:10
 */
public interface QuestionCategoryService {
	
    public QuestionCategoryEntity getByPrimaryKey(QuestionCategoryEntity questionCategoryEntity);
    
    public PageInfo<QuestionCategoryEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map);
    
    public Map<String, Object> save(QuestionCategoryEntity questionCategoryEntity);

	public Integer deleteById(QuestionCategoryEntity questionCategoryEntity);

    public Integer updateById(QuestionCategoryEntity questionCategoryEntity);
}
