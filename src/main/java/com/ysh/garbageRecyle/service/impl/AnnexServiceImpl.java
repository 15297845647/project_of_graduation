package com.ysh.garbageRecyle.service.impl;

import com.ysh.garbageRecyle.service.AnnexService;
import com.ysh.garbageRecyle.entity.AnnexEntity;
import com.ysh.garbageRecyle.mapper.AnnexMapper;

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
 * @date 2020-05-09 11:36:19
 */
@Service("annexServiceImpl")
public class AnnexServiceImpl  implements AnnexService {

    @Autowired
    private AnnexMapper annexMapper;
    

    @Override
    public AnnexEntity getByPrimaryKey(AnnexEntity annexEntity) {
        AnnexEntity entity = annexMapper.selectByPrimaryKey(annexEntity);
        return entity;
    }
    
    @Override
    public PageInfo<AnnexEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
    	PageHelper.startPage(pageNo, pageSize);
		PageInfo<AnnexEntity> page = new PageInfo(annexMapper.selectByCondition(map));
		return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> save(AnnexEntity entity) {
    	entity.setAnnexId(0);
    	annexMapper.insertSelective(entity);
    	
    	Map<String, Object> result = new HashMap<>();
    	result.put("id" , entity.getAnnexId());
    	return result;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteById(AnnexEntity annexEntity){
		return annexMapper.deleteByPrimaryKey(annexEntity);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateById(AnnexEntity annexEntity) {
    	if(null == annexEntity || annexEntity.getAnnexId() <= 0){
    		return 0;
    	}
    	return annexMapper.updateByPrimaryKeySelective(annexEntity);
    }


}
