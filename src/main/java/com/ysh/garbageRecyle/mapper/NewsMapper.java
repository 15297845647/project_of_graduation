package com.ysh.garbageRecyle.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
}
