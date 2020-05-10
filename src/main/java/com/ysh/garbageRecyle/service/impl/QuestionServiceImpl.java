package com.ysh.garbageRecyle.service.impl;

import com.ysh.garbageRecyle.service.QuestionService;
import com.ysh.garbageRecyle.entity.QuestionEntity;
import com.ysh.garbageRecyle.mapper.QuestionMapper;

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
 * @date 2020-05-09 11:36:07
 */
@Service("questionServiceImpl")
public class QuestionServiceImpl  implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    

    @Override
    public QuestionEntity getByPrimaryKey(QuestionEntity questionEntity) {
        QuestionEntity entity = questionMapper.selectByPrimaryKey(questionEntity);
        return entity;
    }
    
    @Override
    public PageInfo<QuestionEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
    	PageHelper.startPage(pageNo, pageSize);
		PageInfo<QuestionEntity> page = new PageInfo(questionMapper.selectByCondition(map));
		return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> save(QuestionEntity entity) {
    	entity.setQuestionId(0);
    	questionMapper.insertSelective(entity);
    	
    	Map<String, Object> result = new HashMap<>();
    	result.put("id" , entity.getQuestionId());
    	return result;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteById(QuestionEntity questionEntity){
		return questionMapper.deleteByPrimaryKey(questionEntity);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateById(QuestionEntity questionEntity) {
    	if(null == questionEntity || questionEntity.getQuestionId() <= 0){
    		return 0;
    	}
    	return questionMapper.updateByPrimaryKeySelective(questionEntity);
    }


}
