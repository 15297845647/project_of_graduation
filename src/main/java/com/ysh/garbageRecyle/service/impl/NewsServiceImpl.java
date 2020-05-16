package com.ysh.garbageRecyle.service.impl;

import com.ysh.garbageRecyle.service.NewsService;
import com.ysh.garbageRecyle.entity.NewsEntity;
import com.ysh.garbageRecyle.mapper.NewsMapper;

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
 * @date 2020-05-08 11:02:22
 */
@Service("newsServiceImpl")
public class NewsServiceImpl  implements NewsService {

    @Autowired
    private NewsMapper newsMapper;
    

    @Override
    public NewsEntity getByPrimaryKey(NewsEntity newsEntity) {
        NewsEntity entity = newsMapper.selectByPrimaryKey(newsEntity);
        return entity;
    }
    
    @Override
    public PageInfo<NewsEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
    	PageHelper.startPage(pageNo, pageSize);
		PageInfo<NewsEntity> page = new PageInfo(newsMapper.selectByCondition(map));
		return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> save(NewsEntity entity) {
    	newsMapper.insertSelective(entity);
    	
    	Map<String, Object> result = new HashMap<>();
    	result.put("id" , entity.getNewsId());
    	return result;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteById(NewsEntity newsEntity){
		return newsMapper.deleteByPrimaryKey(newsEntity);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateById(NewsEntity newsEntity) {
    	if(null == newsEntity || newsEntity.getNewsId() <= 0){
    		return 0;
    	}
    	return newsMapper.updateByPrimaryKeySelective(newsEntity);
    }

	@Override
	public List<String> getAllNewsCode() {
		return newsMapper.getAllNewsCode();
	}

	@Override
	public List<String> getAllCity() {
		return newsMapper.getAllCity();
	}

	@Override
	public List<NewsEntity> selectAllNews() {
		return newsMapper.selectAllNews();
	}


}
