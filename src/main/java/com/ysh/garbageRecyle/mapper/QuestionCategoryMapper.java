package com.ysh.garbageRecyle.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ysh.garbageRecyle.entity.QuestionCategoryEntity;

@Mapper
@Repository
public interface QuestionCategoryMapper extends BaseMapper<QuestionCategoryEntity>  {
	
	
	
}
