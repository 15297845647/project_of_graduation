package com.ysh.garbageRecyle.service;

import com.ysh.garbageRecyle.entity.WrongQuestionEntity;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * 服务实现层接口
 * @author yuxue
 * @date 2020-05-09 11:36:15
 */
public interface WrongQuestionService {
	
    public WrongQuestionEntity getByPrimaryKey(WrongQuestionEntity wrongQuestionEntity);
    
    public PageInfo<WrongQuestionEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map);
    
    public Map<String, Object> save(WrongQuestionEntity wrongQuestionEntity);

	public Integer deleteById(WrongQuestionEntity wrongQuestionEntity);

    public Integer updateById(WrongQuestionEntity wrongQuestionEntity);
}
