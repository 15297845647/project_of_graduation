package com.ysh.garbageRecyle.mapper;

import com.ysh.garbageRecyle.entity.GarbageCategoryEntity;
import java.util.List;
import java.util.Map;

import com.ysh.garbageRecyle.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GarbageCategoryMapper extends BaseMapper<GarbageCategoryEntity>{

}