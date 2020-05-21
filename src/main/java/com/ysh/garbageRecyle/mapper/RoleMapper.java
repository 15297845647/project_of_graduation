package com.ysh.garbageRecyle.mapper;

import com.ysh.garbageRecyle.dto.UserCountDto;
import com.ysh.garbageRecyle.entity.RoleEntity;
import java.util.List;
import java.util.Map;

import com.ysh.garbageRecyle.entity.UsersEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper extends BaseMapper<RoleEntity> {

    //查询所有角色
    public List<RoleEntity> selectAllRole();

    //角色统计
    @Select("SELECT count( * ) AS roleCount,u.role_id AS roleId,(SELECT count(*) FROM users) As sumUser,(SELECT role_name from role WHERE u.role_id=role_id) as roleName FROM users u GROUP BY u.role_id")
    @Results({@Result(property="roleCount",column="roleCount"),@Result(property="roleId",column="roleId"),@Result(property="sumUser",column="sumUser"),@Result(property="roleName",column="roleName")})
    List<UserCountDto> countUserRole();
}