package com.ysh.garbageRecyle.service;

import com.ysh.garbageRecyle.entity.GarbageLawEntity;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


/**
 * 服务实现层接口
 * @author yuxue
 * @date 2020-05-08 11:01:43
 */
public interface GarbageLawService {
	
    public GarbageLawEntity getByPrimaryKey(GarbageLawEntity garbageLawEntity);
    
    public PageInfo<GarbageLawEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map);
    
    public Map<String, Object> save(GarbageLawEntity garbageLawEntity);

	public Integer deleteById(GarbageLawEntity garbageLawEntity);

    public Integer updateById(GarbageLawEntity garbageLawEntity);

    /*
    * 获取所有法律法规code
    */
    public List<String> getAllCode();
    /*
    *获取所有城市
    */
    public List<String> getAllLawCity();

    //获取所有法律法规
    public List<GarbageLawEntity> selectAllLaws();
}
