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
    /*
    *根据名字精确查询
    */
    public GarbageEntity findByname(String garbageName);
    //查询所有垃圾
    public List<GarbageEntity> selectAllGarbage();
    //根据垃圾代码查询所有垃圾信息
    public List<GarbageEntity> selecrGarbageByCode(GarbageEntity garbageEntity);
}