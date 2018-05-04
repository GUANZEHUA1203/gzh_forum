package com.gzeh.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzeh.forum.services.IBlockService;

/**
 *
 * @author gzh
 * @since 2018-04-21
 */
@Controller
@RequestMapping("/block")
public class BlockController {
	@Autowired
	private IBlockService iBlockService;
	
	@RequestMapping("/add")
	@ResponseBody
	public Object insertBlock(String blockJson){
//		{id：0,}
		return blockJson;
	}
	
}
