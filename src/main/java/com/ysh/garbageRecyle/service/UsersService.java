package com.ysh.garbageRecyle.service;

import com.ysh.garbageRecyle.dto.UserCountDto;
import com.ysh.garbageRecyle.entity.UsersEntity;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 服务实现层接口
 * @author yuxue
 * @date 2020-05-04 13:56:42
 */
public interface UsersService {
	
    public UsersEntity getByPrimaryKey(UsersEntity users);
    
    public PageInfo<UsersEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map);
    
    public Map<String, Object> save(UsersEntity usersEntity);

	public Integer deleteById(UsersEntity users);

    public Integer updateById(UsersEntity usersEntity);

    public List<UsersEntity> selectAll();

    //用户登录
    public UsersEntity userLogin(UsersEntity usersEntity);

    //查询所有的账号
    public List<String> selectAllAccountNumber();

    //查询所有手机号
    public List<String> selectAllPhoneNumber();

}
