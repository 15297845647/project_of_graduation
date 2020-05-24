package com.ysh.garbageRecyle.service;

import com.ysh.garbageRecyle.dto.GarbageByFirstChar;
import com.ysh.garbageRecyle.dto.GarbageFirstCharDto;
import com.ysh.garbageRecyle.entity.GarbageEntity;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 服务实现层接口
 * @author yuxue
 * @date 2020-05-05 10:10:02
 */
public interface GarbageService {
	
    public GarbageEntity getByPrimaryKey(GarbageEntity garbageEntity);
    
    public PageInfo<GarbageEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map);
    
    public Map<String, Object> save(GarbageEntity garbageEntity);

	public Integer deleteById(GarbageEntity garbageEntity);

    public Integer updateById(GarbageEntity garbageEntity);

    public List<GarbageEntity> getTopGarbage();

    public List<GarbageEntity> findGarbageByName(GarbageEntity entity);

    public GarbageEntity findByEqualName(String garbageName);

    public List<GarbageEntity> selectAllGarbage();

    public List<GarbageEntity> selectByGarbageCategotyCode(GarbageEntity entity);
    //查首字母列表
   public List<GarbageByFirstChar> selectAllGarbageFirstChar(int categoryCode);
    //查垃圾带首字母的
    public List<GarbageFirstCharDto> selectAllGarbageBychar(int categoryCode);
}
