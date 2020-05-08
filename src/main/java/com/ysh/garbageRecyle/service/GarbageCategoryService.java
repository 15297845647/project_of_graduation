package com.ysh.garbageRecyle.service;

import com.ysh.garbageRecyle.entity.GarbageCategoryEntity;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * 服务实现层接口
 * @author yuxue
 * @date 2020-05-05 10:09:34
 */
public interface GarbageCategoryService {
	
    public GarbageCategoryEntity getByPrimaryKey(GarbageCategoryEntity garbageCategoryEntity);
    
    public PageInfo<GarbageCategoryEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map);
    
    public Map<String, Object> save(GarbageCategoryEntity garbageCategoryEntity);

	public Integer deleteById(GarbageCategoryEntity garbageCategoryEntity);

    public Integer updateById(GarbageCategoryEntity garbageCategoryEntity);
}
