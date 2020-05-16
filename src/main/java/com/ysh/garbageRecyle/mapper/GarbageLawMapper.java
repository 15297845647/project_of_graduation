package com.ysh.garbageRecyle.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ysh.garbageRecyle.entity.GarbageLawEntity;

@Mapper
@Repository
public interface GarbageLawMapper extends BaseMapper<GarbageLawEntity>  {
	
    List<String> getAllCode();

    List<String> getAllLawCity();

    //查询所有的法律法规
    List<GarbageLawEntity> selectAllLaws();
	
}
