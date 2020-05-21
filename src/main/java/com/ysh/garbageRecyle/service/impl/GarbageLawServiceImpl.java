package com.ysh.garbageRecyle.service.impl;

import com.ysh.garbageRecyle.dto.LawCountDto;
import com.ysh.garbageRecyle.service.GarbageLawService;
import com.ysh.garbageRecyle.entity.GarbageLawEntity;
import com.ysh.garbageRecyle.mapper.GarbageLawMapper;

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
 * @date 2020-05-08 11:01:43
 */
@Service("garbageLawServiceImpl")
public class GarbageLawServiceImpl  implements GarbageLawService {

    @Autowired
    private GarbageLawMapper garbageLawMapper;
    

    @Override
    public GarbageLawEntity getByPrimaryKey(GarbageLawEntity garbageLawEntity) {
        GarbageLawEntity entity = garbageLawMapper.selectByPrimaryKey(garbageLawEntity);
        return entity;
    }
    
    @Override
    public PageInfo<GarbageLawEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
    	PageHelper.startPage(pageNo, pageSize);
		PageInfo<GarbageLawEntity> page = new PageInfo(garbageLawMapper.selectByCondition(map));
		return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> save(GarbageLawEntity entity) {
    	garbageLawMapper.insertSelective(entity);
    	Map<String, Object> result = new HashMap<>();
    	result.put("id" , entity.getLawId());
    	return result;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteById(GarbageLawEntity garbageLawEntity){
		return garbageLawMapper.deleteByPrimaryKey(garbageLawEntity);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateById(GarbageLawEntity garbageLawEntity) {
    	if(null == garbageLawEntity || garbageLawEntity.getLawId() <= 0){
    		return 0;
    	}
    	return garbageLawMapper.updateByPrimaryKeySelective(garbageLawEntity);
    }

    @Override
    public List<String> getAllCode() {
        return garbageLawMapper.getAllCode();
    }

    @Override
    public List<String> getAllLawCity() {
        return garbageLawMapper.getAllLawCity();
    }

    @Override
    public List<GarbageLawEntity> selectAllLaws() {
        return garbageLawMapper.selectAllLaws();
    }

    @Override
    public List<LawCountDto> getLawCount() {
        return garbageLawMapper.getLawCount();
    }


}
