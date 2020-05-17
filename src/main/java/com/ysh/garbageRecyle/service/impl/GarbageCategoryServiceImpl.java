package com.ysh.garbageRecyle.service.impl;

import com.ysh.garbageRecyle.service.GarbageCategoryService;
import com.ysh.garbageRecyle.entity.GarbageCategoryEntity;
import com.ysh.garbageRecyle.mapper.GarbageCategoryMapper;

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
 * @date 2020-05-05 10:09:34
 */
@Service("garbageCategoryServiceImpl")
public class GarbageCategoryServiceImpl  implements GarbageCategoryService {

    @Autowired
    private GarbageCategoryMapper garbageCategoryMapper;
    

    @Override
    public GarbageCategoryEntity getByPrimaryKey(GarbageCategoryEntity garbageCategoryEntity) {
        GarbageCategoryEntity entity = garbageCategoryMapper.selectByPrimaryKey(garbageCategoryEntity);
        return entity;
    }
    
    @Override
    public PageInfo<GarbageCategoryEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
    	PageHelper.startPage(pageNo, pageSize);
		PageInfo<GarbageCategoryEntity> page = new PageInfo(garbageCategoryMapper.selectByCondition(map));
		return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> save(GarbageCategoryEntity entity) {
    	int i=garbageCategoryMapper.insertSelective(entity);
    	Map<String, Object> result = new HashMap<>();
    	result.put("result" ,i);
    	return result;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteById(GarbageCategoryEntity garbageCategoryEntity){
		return garbageCategoryMapper.deleteByPrimaryKey(garbageCategoryEntity);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateById(GarbageCategoryEntity garbageCategoryEntity) {
    	if(null == garbageCategoryEntity || garbageCategoryEntity.getCategoryId() <= 0){
    		return 0;
    	}
    	return garbageCategoryMapper.updateByPrimaryKeySelective(garbageCategoryEntity);
    }


}
