package com.ysh.garbageRecyle.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * mapper 继承类
 * @param <T>
 * @author ysh
 * @date 2020-05-04 13:56:42
 */
public interface BaseMapper<T> {
	
	T selectByPrimaryKey(T record);

	List<T> selectByCondition(Map<String, Object> map);
	
	int insert(T record);

	int insertSelective(T record);

	int updateByPrimaryKey(T record);
	
	int updateByPrimaryKeySelective(T record);

	int deleteByPrimaryKey(T record);

}
