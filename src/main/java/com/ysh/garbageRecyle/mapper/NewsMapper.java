package com.ysh.garbageRecyle.mapper;

import java.util.List;
import java.util.Map;

import com.ysh.garbageRecyle.dto.NewsCountDto;
import com.ysh.garbageRecyle.dto.QuestionDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.ysh.garbageRecyle.entity.NewsEntity;

@Mapper
@Repository
public interface NewsMapper extends BaseMapper<NewsEntity>  {

    public List<String> getAllNewsCode();
	/*
    *获取所有城市
	*/
	public List<String> getAllCity();
	//获取所有新闻
	public List<NewsEntity> selectAllNews();

	//新闻统计
	@Select("SELECT\n" +
			"\tn.city AS newsCity,\n" +
			"\t(SELECT count(*) from news ne where ne.city=n.city ) AS cityNewsNumber,\n" +
			"\t(select count(*) from news) AS sumNewsNumber,\n" +
			"\t(select sum(see_times) from news) AS sumSeeTimes,\n" +
			"\tsum(n.see_times) AS seeTimes\n" +
			"FROM\n" +
			"\tnews n\n" +
			"GROUP BY newsCity")
	@Results({@Result(column = "newsCity",property = "newsCity"),@Result(column = "cityNewsNumber",property = "cityNewsNumber"),@Result(column = "sumNewsNumber",property = "sumNewsNumber"),@Result(column = "seeTimes",property = "seeTimes"),@Result(column = "sumSeeTimes",property = "sumSeeTimes")})
	public List<NewsCountDto> getNewsCount();
}
