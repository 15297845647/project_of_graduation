package com.ysh.garbageRecyle.service;

import com.ysh.garbageRecyle.entity.RoleEntity;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * 服务实现层接口
 * @author yuxue
 * @date 2020-05-05 00:05:20
 */
public interface RoleService {
	
    public RoleEntity getByPrimaryKey(RoleEntity roleEntity);
    
    public PageInfo<RoleEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map);
    
    public Map<String, Object> save(RoleEntity roleEntity);

	public Integer deleteById(RoleEntity roleEntity);

    public Integer updateById(RoleEntity roleEntity);
}
