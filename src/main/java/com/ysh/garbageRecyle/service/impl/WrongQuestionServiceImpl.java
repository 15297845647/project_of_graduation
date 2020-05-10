package com.ysh.garbageRecyle.service.impl;

import com.ysh.garbageRecyle.service.WrongQuestionService;
import com.ysh.garbageRecyle.entity.WrongQuestionEntity;
import com.ysh.garbageRecyle.mapper.WrongQuestionMapper;

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
 * @date 2020-05-09 11:36:15
 */
@Service("wrongQuestionServiceImpl")
public class WrongQuestionServiceImpl  implements WrongQuestionService {

    @Autowired
    private WrongQuestionMapper wrongQuestionMapper;
    

    @Override
    public WrongQuestionEntity getByPrimaryKey(WrongQuestionEntity wrongQuestionEntity) {
        WrongQuestionEntity entity = wrongQuestionMapper.selectByPrimaryKey(wrongQuestionEntity);
        return entity;
    }
    
    @Override
    public PageInfo<WrongQuestionEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
    	PageHelper.startPage(pageNo, pageSize);
		PageInfo<WrongQuestionEntity> page = new PageInfo(wrongQuestionMapper.selectByCondition(map));
		return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> save(WrongQuestionEntity entity) {
    	entity.setWqId(0);
    	wrongQuestionMapper.insertSelective(entity);
    	
    	Map<String, Object> result = new HashMap<>();
    	result.put("id" , entity.getWqId());
    	return result;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteById(WrongQuestionEntity wrongQuestionEntity){
		return wrongQuestionMapper.deleteByPrimaryKey(wrongQuestionEntity);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateById(WrongQuestionEntity wrongQuestionEntity) {
    	if(null == wrongQuestionEntity || wrongQuestionEntity.getWqId() <= 0){
    		return 0;
    	}
    	return wrongQuestionMapper.updateByPrimaryKeySelective(wrongQuestionEntity);
    }


}
