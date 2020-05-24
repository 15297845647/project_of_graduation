package com.ysh.garbageRecyle.mapper;

import com.ysh.garbageRecyle.dto.GarbageByFirstChar;
import com.ysh.garbageRecyle.dto.GarbageFirstCharDto;
import com.ysh.garbageRecyle.entity.GarbageEntity;
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

    //查询所有垃圾首字母
    @Select("SELECT\n" +
            "DISTINCT(lower(case when g.garbage_name REGEXP '^[a-zA-Z]' then LEFT(g.garbage_name, 1)\n" +
            "\t\twhen g.garbage_name REGEXP '^[0-9]' then LEFT(g.garbage_name, 1)\n" +
            "\t\telse ELT(INTERVAL(CONV(HEX(LEFT(CONVERT(g.garbage_name USING gbk),1)),16,10),\n" +
            "\t\t\t\t\t\t0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,\n" +
            "\t\t\t\t\t\t0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,0xC8F6,\n" +
            "\t\t\t\t\t\t0xCBFA,0xCDDA,0xCEF4,0xD1B9,0xD4D1),\n" +
            "\t\t\t\t\t\t'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P',\n" +
            "\t\t\t\t\t\t'Q','R','S','T','W','X','Y','Z') end) ) AS firstChar\n" +
            "\t\n" +
            "from garbage g \n" +
            "where g.garbage_category_code=#{categoryCode}\n" +
            "order by firstChar asc")
    @Results({@Result(column = "firstChar",property = "firstChar")})
    public List<GarbageByFirstChar> selectAllGarbageFirstChar(int categoryCode);
    //查询所有垃圾带首字母的
    @Select("SELECT\n" +
            "g.garbage_id AS garbageId,\n" +
            "g.garbage_name AS garbageName,\n" +
            "g.garbage_category_code AS garbageCategoryCode,\n" +
            "lower(case when g.garbage_name REGEXP '^[a-zA-Z]' then LEFT(g.garbage_name, 1)\n" +
            "\t\twhen g.garbage_name REGEXP '^[0-9]' then LEFT(g.garbage_name, 1)\n" +
            "\t\telse ELT(INTERVAL(CONV(HEX(LEFT(CONVERT(g.garbage_name USING gbk),1)),16,10),\n" +
            "\t\t\t\t\t\t0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,\n" +
            "\t\t\t\t\t\t0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,0xC8F6,\n" +
            "\t\t\t\t\t\t0xCBFA,0xCDDA,0xCEF4,0xD1B9,0xD4D1),\n" +
            "\t\t\t\t\t\t'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P',\n" +
            "\t\t\t\t\t\t'Q','R','S','T','W','X','Y','Z') end)  AS firstChar\n" +
            "\t\n" +
            "from garbage g \n" +
            "where g.garbage_category_code=#{categoryCode}\n" +
            "order by firstChar asc")
    @Results({@Result(column = "firstChar",property = "firstChar"),@Result(column = "garbageId",property = "garbageId"),@Result(column = "garbageName",property = "garbageName"),@Result(column = "garbageCategoryCode",property = "garbageCategoryCode")})
    public List<GarbageFirstCharDto> selectAllGarbageBychar(int categoryCode);
}