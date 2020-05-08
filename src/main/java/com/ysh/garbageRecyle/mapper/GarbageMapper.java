package com.ysh.garbageRecyle.mapper;

import com.ysh.garbageRecyle.entity.GarbageEntity;
import java.util.List;
import java.util.Map;

import com.ysh.garbageRecyle.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GarbageMapper extends BaseMapper<GarbageEntity>{
    public List<GarbageEntity> getTopGarbage();
    public List<GarbageEntity> findGarbageByCondition(GarbageEntity GarbageEntity);
}