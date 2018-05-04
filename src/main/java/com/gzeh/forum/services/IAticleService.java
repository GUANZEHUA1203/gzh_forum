package com.gzeh.forum.services;

import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.gzeh.forum.bean.Aticle;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzh
 * @since 2018-04-21
 */
public interface IAticleService extends IService<Aticle> {
	
	/**
	 * @param param
	 * 列表信息
	 * @return
	 */
	public Page<Map<String, Object>> pageInfoList(Page<Map<String, Object>>  pageInfo,Map<String, Object> param);
	
}
