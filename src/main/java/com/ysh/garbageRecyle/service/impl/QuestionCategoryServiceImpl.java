package com.ysh.garbageRecyle.service.impl;

import com.ysh.garbageRecyle.service.QuestionCategoryService;
import com.ysh.garbageRecyle.entity.QuestionCategoryEntity;
import com.ysh.garbageRecyle.mapper.QuestionCategoryMapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务实现层
 * @author yuxue
 * @date 2020-05-09 11:36:10
 */
@Service("questionCategoryServiceImpl")
public class QuestionCategoryServiceImpl  implements QuestionCategoryService {

    @Autowired
    private QuestionCategoryMapper questionCategoryMapper;
    

    @Override
    public QuestionCategoryEntity getByPrimaryKey(QuestionCategoryEntity questionCategoryEntity) {
        QuestionCategoryEntity entity = questionCategoryMapper.selectByPrimaryKey(questionCategoryEntity);
        return entity;
    }
    
    @Override
    public PageInfo<QuestionCategoryEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
    	PageHelper.startPage(pageNo, pageSize);
		PageInfo<QuestionCategoryEntity> page = new PageInfo(questionCategoryMapper.selectByCondition(map));
		return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> save(QuestionCategoryEntity entity) {
    	entity.setQcId(0);
    	questionCategoryMapper.insertSelective(entity);
    	
    	Map<String, Object> result = new HashMap<>();
    	result.put("id" , entity.getQcId());
    	return result;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteById(QuestionCategoryEntity questionCategoryEntity){
		return questionCategoryMapper.deleteByPrimaryKey(questionCategoryEntity);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateById(QuestionCategoryEntity questionCategoryEntity) {
    	if(null == questionCategoryEntity || questionCategoryEntity.getQcId() <= 0){
    		return 0;
    	}
    	return questionCategoryMapper.updateByPrimaryKeySelective(questionCategoryEntity);
    }


}
