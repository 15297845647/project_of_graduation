package com.ysh.garbageRecyle.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ysh.garbageRecyle.entity.QuestionEntity;

@Mapper
@Repository
public interface QuestionMapper extends BaseMapper<QuestionEntity>  {
	
	
	
}
