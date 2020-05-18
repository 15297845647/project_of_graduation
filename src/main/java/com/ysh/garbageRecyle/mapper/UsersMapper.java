package com.ysh.garbageRecyle.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.ysh.garbageRecyle.entity.UsersEntity;

@Mapper
@Repository
public interface UsersMapper extends BaseMapper<UsersEntity>  {

    //查询所有用户
    List<UsersEntity> selectAllUser();

    //用户登录
    UsersEntity userLogin(UsersEntity usersEntity);

    //查询所有的账号
    List<String> selectAllAccountNumber();

    //查询所有账户手机号
    List<String> selectAllPhoneNumber();
}
