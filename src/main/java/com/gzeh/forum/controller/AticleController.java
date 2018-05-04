package com.gzeh.forum.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.gzeh.forum.base.BaseController;
import com.gzeh.forum.base.annotation.AnnAuthType;
import com.gzeh.forum.base.annotation.AuthPermissions;
import com.gzeh.forum.bean.Account;
import com.gzeh.forum.bean.Aticle;
import com.gzeh.forum.bean.BlockSummary;
import com.gzeh.forum.common.SystemConfig;
import com.gzeh.forum.services.IAccountService;
import com.gzeh.forum.services.IAticleService;
import com.gzeh.forum.services.IBlockSummaryService;
import com.gzeh.forum.util.SerializeUtils;
import com.gzeh.forum.util.StringUtil;

/**
 * @author gzh
 * @since 2018-04-21
 */
@Controller
@RequestMapping("/aticle")
public class AticleController extends BaseController {
	
	@Autowired
	IAccountService iAccountService;
	
	@Autowired
	IAticleService aticleService;
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	@AuthPermissions(authName = AnnAuthType.user)
	public Object posted(String title,String content,String blockid){
		Long blockLong = StringUtil.getLong(blockid);
		
		Account selectById = iAccountService.selectById(getUserId());
		Date lastTime = selectById.getLastTime();
		//时间间隔
		if(lastTime==null||new Date().getTime()-lastTime.getTime()>SystemConfig.ATICLE_TIME){
			Aticle aticle = new Aticle(title,getUserId(),SerializeUtils.serialize(content),new Date(),blockLong);
			aticle.setAtId(idGenerator.nextId());
			this.aticleService.insert(aticle);
		}else{
			return renderError("稍后再发送帖子");
		}
		//更新发帖时间和等级
		updateLevelorLastTime(5, new Date());
		//更新模块详细信息
		updateBlock(blockLong);
		return renderSuccess();
	}
	
	
	
	@RequestMapping("/update")
	@AuthPermissions(authName = AnnAuthType.user)
	public Object updateActicle(Aticle aticle){
		this.aticleService.updateById(aticle);
		return renderSuccess();
	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	@AuthPermissions(authName = AnnAuthType.user)
	public Object getAticleList(@RequestParam(value="blockid",required=true) String blockid,int currentpage,int pagesize ){
		
		Map<String, Object> paramMap = Maps.newHashMap();
		Page<Map<String, Object>> paramPage=new Page<>(currentpage,pagesize);
		
		paramMap.put("blockid", blockid);
		Page<Map<String, Object>> pageInfoList = this.aticleService.pageInfoList(paramPage,paramMap);
		
		return renderSuccess(pageInfoList);
	}
	
	@RequestMapping("/del")
	@ResponseBody
	@AuthPermissions(authName = AnnAuthType.user)
	public Object deleteAticle(@RequestParam(value="atid",required=true) String atid){
		Aticle ac=new Aticle();
		ac.setAcountId(StringUtil.getLong(atid));
		EntityWrapper<Aticle> ew=new EntityWrapper<Aticle>(ac);
		 aticleService.delete(ew);
		return renderSuccess();
	}
	
	
	
	
}
