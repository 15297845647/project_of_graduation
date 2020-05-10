package com.ysh.garbageRecyle.service;

import com.ysh.garbageRecyle.entity.NewsEntity;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 服务实现层接口
 * @author yuxue
 * @date 2020-05-08 11:02:22
 */
public interface NewsService {
	
    public NewsEntity getByPrimaryKey(NewsEntity newsEntity);
    
    public PageInfo<NewsEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map);
    
    public Map<String, Object> save(NewsEntity newsEntity);

	public Integer deleteById(NewsEntity newsEntity);

    public Integer updateById(NewsEntity newsEntity);
    /*
    * 获取所有新闻的代码
    */
    public List<String> getAllNewsCode();
    /*
    *获取所有的新闻的城市
    */
    public List<String> getAllCity();
}
