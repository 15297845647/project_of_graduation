package com.ysh.garbageRecyle.service.impl;

import com.ysh.garbageRecyle.service.UsersService;
import com.ysh.garbageRecyle.entity.UsersEntity;
import com.ysh.garbageRecyle.mapper.UsersMapper;

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
 * @date 2020-05-04 13:56:42
 */
@Service("usersServiceImpl")
public class UsersServiceImpl  implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    

    @Override
    public UsersEntity getByPrimaryKey(UsersEntity users) {
        UsersEntity entity = usersMapper.selectByPrimaryKey(users);
        return entity;
    }
    
    @Override
    public PageInfo<UsersEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
    	PageHelper.startPage(pageNo, pageSize);
		PageInfo<UsersEntity> page = new PageInfo(usersMapper.selectByCondition(map));
		return page;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, Object> save(UsersEntity entity) {
    	int i=usersMapper.insertSelective(entity);
    	Map<String, Object> result = new HashMap<>();
    	result.put("id" , i);
    	return result;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer deleteById(UsersEntity users){
		return usersMapper.deleteByPrimaryKey(users);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer updateById(UsersEntity usersEntity) {
    	if(null == usersEntity || usersEntity.getUserId() <= 0){
    		return 0;
    	}
    	return usersMapper.updateByPrimaryKeySelective(usersEntity);
    }

	@Override
	public List<UsersEntity> selectAll() {
		return usersMapper.selectAllUser();
	}

	@Override
	public UsersEntity userLogin(UsersEntity usersEntity) {
		return usersMapper.userLogin(usersEntity);
	}


}
