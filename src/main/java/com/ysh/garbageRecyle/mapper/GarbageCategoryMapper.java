package com.ysh.garbageRecyle.mapper;

import com.ysh.garbageRecyle.dto.GarbageCountDto;
import com.ysh.garbageRecyle.entity.GarbageCategoryEntity;
import java.util.List;
import java.util.Map;

import com.ysh.garbageRecyle.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GarbageCategoryMapper extends BaseMapper<GarbageCategoryEntity>{

    //根据垃圾类别进行查询
    @Select("SELECT\n" +
            "\tgc.category_id AS categoryId,\n" +
            "\tgc.category_name AS categoryName,\n" +
            "\t( SELECT count( g.garbage_id ) FROM garbage g WHERE g.garbage_category_code = gc.category_code ) AS garbageNumbers,\n" +
            "\tgc.query_times AS categoryQueryTime from \n" +
            "\tgarbage_category gc")
    @Results({@Result(property="categoryId",column="categoryId"),@Result(property="categoryName",column="categoryName"),@Result(property="garbageNumbers",column="garbageNumbers"),@Result(property="categoryQueryTime",column="categoryQueryTime")})
    public List<GarbageCountDto> getGarbageCount();
}