package com.ysh.garbageRecyle.service.impl;

import com.ysh.garbageRecyle.dto.UserCountDto;
import com.ysh.garbageRecyle.service.RoleService;
import com.ysh.garbageRecyle.entity.RoleEntity;
import com.ysh.garbageRecyle.mapper.RoleMapper;

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
 * @date 2020-05-05 00:05:20
 */
@Service("roleServiceImpl")
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    

    @Override
    public RoleEntity getByPrimaryKey(RoleEntity roleEntity) {
        RoleEntity entity = roleMapper.selectByPrimaryKey(roleEntity);
        return entity;
    }
    
    @Override
    public PageInfo<RoleEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
    	PageHelper.startPage(pageNo, pageSize);
		PageInfo<RoleEntity> page = new PageInfo(roleMapper.selectByCondition(map));
		return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> save(RoleEntity entity) {
    	int i=roleMapper.insertSelective(entity);
    	
    	Map<String, Object> result = new HashMap<>();
    	result.put("id" , i);
    	return result;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteById(RoleEntity roleEntity){
		return roleMapper.deleteByPrimaryKey(roleEntity);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateById(RoleEntity roleEntity) {
    	if(null == roleEntity || roleEntity.getRoleId() <= 0){
    		return 0;
    	}
    	return roleMapper.updateByPrimaryKeySelective(roleEntity);
    }

	@Override
	public List<RoleEntity> selectAllRole() {
		return roleMapper.selectAllRole();
	}

	@Override
	public List<UserCountDto> userRoleCount() {
		return roleMapper.countUserRole();
	}


}
