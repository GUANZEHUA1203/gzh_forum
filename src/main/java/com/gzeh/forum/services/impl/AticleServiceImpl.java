package com.gzeh.forum.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gzeh.forum.bean.Aticle;
import com.gzeh.forum.dao.AticleMapper;
import com.gzeh.forum.services.IAticleService;
import com.gzeh.forum.util.BeanUtils;
import com.gzeh.forum.util.StringUtil;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzh
 * @since 2018-04-21
 */
@Service
public class AticleServiceImpl extends ServiceImpl<AticleMapper, Aticle> implements IAticleService {
	
	@Autowired
	AticleMapper am;

	@Override
	public Page<Map<String, Object>> pageInfoList(Page<Map<String, Object>>  pageInfo,Map<String, Object> param) {
		
		Page<Map<String, Object>> pagetmp = BeanUtils.copy(param, Page.class);//原分页信息
		
	    List<Map<String, Object>> pageInfoAticle = this.am.pageInfoAticle(pageInfo,param);
	    
	    Aticle aticle = new Aticle();
	    aticle.setBlockId(StringUtil.getLong(param.get("blockid")));
	    EntityWrapper<Aticle> ew=new EntityWrapper<Aticle>(aticle);
	    Integer selectCount = am.selectCount(ew);
	    
	    pagetmp.setRecords(pageInfoAticle);
	    pagetmp.setTotal(selectCount);
	    return pagetmp;
	}
	
}
