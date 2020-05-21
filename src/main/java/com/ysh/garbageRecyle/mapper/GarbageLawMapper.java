package com.ysh.garbageRecyle.mapper;

import java.util.List;
import java.util.Map;

import com.ysh.garbageRecyle.dto.LawCountDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.ysh.garbageRecyle.entity.GarbageLawEntity;

@Mapper
@Repository
public interface GarbageLawMapper extends BaseMapper<GarbageLawEntity>  {
	
    List<String> getAllCode();

    List<String> getAllLawCity();

    //��ѯ���еķ��ɷ���
    List<GarbageLawEntity> selectAllLaws();

    //���ɷ�������ͳ��
    @Select("SELECT\n" +
            "\tgl.law_location AS city,\n" +
            "\tcount(*) AS sumLawsNumber,\n" +
            "\tsum(gl.see_times) AS seeTimes,\n" +
            "\t(SELECT sum(see_times) from garbage_law) AS sumSeeTimes\n" +
            "FROM\n" +
            "\tgarbage_law gl\n" +
            "GROUP BY city")
    @Results({@Result(column = "city",property = "city"),@Result(column = "sumLawsNumber",property = "sumLawsNumber"),@Result(column = "seeTimes",property = "seeTimes"),@Result(column = "sumSeeTimes",property = "sumSeeTimes")})
    List<LawCountDto> getLawCount();
	
}
