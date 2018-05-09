package com.gzeh.forum.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.gzeh.forum.base.BaseController;
import com.gzeh.forum.bean.Block;
import com.gzeh.forum.services.IBlockService;
import com.gzeh.forum.util.IdGenerator;
import com.gzeh.forum.util.Object2mapUtil;

/**
 *
 * @author gzh
 * @since 2018-04-21
 */
@Controller
@RequestMapping("/block")
public class BlockController extends BaseController {
	@Autowired
	private IBlockService iBlockService;
	
	
	@GetMapping("/addpage")
	public Object addpage() {
		return "/admin/block/blockAdd";
		
	}
	@RequestMapping("/add")
	@ResponseBody
	public Object insertBlock(String blockName,String listChildName){
		Block block=new Block();
		long nextId = idGenerator.nextId();
		block.setBlId(nextId);
		block.setBlName(blockName);
		iBlockService.insert(block);
		String[] split = listChildName.split(",");
		for (String string : split) {
			Block blockChild=new Block();
			blockChild.setBlId(idGenerator.nextId());
			blockChild.setBlName(string);
			blockChild.setBlParent(nextId);
			iBlockService.insert(block);
		}
		return renderSuccess();
	}
	
	
	@GetMapping("/editpage")
	public Object editpage(Model model, Long blockid) {
		Block selectById = this.iBlockService.selectById(blockid);
		model.addAttribute("block", selectById);
		return "/admin/block/blockEdit";
		
	}
	@RequestMapping("/update")
	@ResponseBody
	public Object update(Block block){
		boolean updateById = this.iBlockService.updateById(block);
		return renderSuccess();
	}
	

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Long blockid){
		this.iBlockService.deleteById(blockid);
		return renderSuccess();
	}
	
	@GetMapping("/manager")
	public Object getManager() {
		return "/admin/block/block";
		
	}
	
	@PostMapping(value="/list")
    @ResponseBody
    public Object dataGrid() {
		ArrayList<Object> newArrayList = Lists.newArrayList();
		List<Block> selectList = iBlockService.selectList(null);
		for (Block block : selectList) {
			Map<String, Object> objectToMap;
			try {
				objectToMap = Object2mapUtil.objectToMap(block);
				objectToMap.put("id", objectToMap.get("blId"));
				newArrayList.add(objectToMap);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		
		}
		
		return  newArrayList;
    }
	
	@PostMapping(value="/listtree")
    @ResponseBody
    public Object listtree() {
		return  iBlockService.getBlockList();
    }
	
}
