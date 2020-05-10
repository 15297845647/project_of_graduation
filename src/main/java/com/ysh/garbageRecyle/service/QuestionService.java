package com.ysh.garbageRecyle.service;

import com.ysh.garbageRecyle.entity.QuestionEntity;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
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
}
