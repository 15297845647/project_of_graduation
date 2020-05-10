package com.ysh.garbageRecyle.service;

import com.ysh.garbageRecyle.entity.AnnexEntity;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.Map;


/**
 * 服务实现层接口
 * @author yuxue
 * @date 2020-05-09 11:36:19
 */
public interface AnnexService {
	
    public AnnexEntity getByPrimaryKey(AnnexEntity annexEntity);
    
    public PageInfo<AnnexEntity> queryByPage(Integer pageNo, Integer pageSize, Map<String, Object> map);
    
    public Map<String, Object> save(AnnexEntity annexEntity);

	public Integer deleteById(AnnexEntity annexEntity);

    public Integer updateById(AnnexEntity annexEntity);
}
