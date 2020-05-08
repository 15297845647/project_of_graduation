package com.ysh.garbageRecyle.service.impl;

import com.ysh.garbageRecyle.service.GarbageService;
import com.ysh.garbageRecyle.entity.GarbageEntity;
import com.ysh.garbageRecyle.mapper.GarbageMapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 * @author yuxue
 * @date 2020-05-05 10:10:02
 */
@Service("garbageServiceImpl")
public class GarbageServiceImpl  implements GarbageService {

    @Autowired
    private GarbageMapper garbageMapper;
    

    @Override
    public GarbageEntity getByPrimaryKey(GarbageEntity garbageEntity) {
        GarbageEntity entity = garbageMapper.selectByPrimaryKey(garbageEntity);
        return entity;
    }
    
    @Override
    public PageInfo<GarbageEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
    	PageHelper.startPage(pageNo, pageSize);
		PageInfo<GarbageEntity> page = new PageInfo(garbageMapper.selectByCondition(map));
		return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> save(GarbageEntity entity) {
    	entity.setGarbageId(0);
    	garbageMapper.insertSelective(entity);
    	
    	Map<String, Object> result = new HashMap<>();
    	result.put("id" , entity.getGarbageId());
    	return result;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteById(GarbageEntity garbageEntity){
		return garbageMapper.deleteByPrimaryKey(garbageEntity);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateById(GarbageEntity garbageEntity) {
    	if(null == garbageEntity || garbageEntity.getGarbageId() <= 0){
    		return 0;
    	}
    	return garbageMapper.updateByPrimaryKeySelective(garbageEntity);
    }

	@Override
	public List<GarbageEntity> getTopGarbage() {
		return garbageMapper.getTopGarbage();
	}

	@Override
	public List<GarbageEntity> findGarbageByName(GarbageEntity entity) {
		return garbageMapper.findGarbageByCondition(entity);
	}


}
