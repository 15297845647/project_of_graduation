package com.ysh.garbageRecyle.mapper;

import com.ysh.garbageRecyle.entity.RoleEntity;
import java.util.List;
import java.util.Map;

import com.ysh.garbageRecyle.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<RoleEntity> {

    //查询所有角色
    public List<RoleEntity> selectAllRole();
}