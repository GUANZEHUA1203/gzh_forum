package com.gzeh.forum.services.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gzeh.forum.bean.Block;
import com.gzeh.forum.dao.BlockMapper;
import com.gzeh.forum.services.IBlockService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gzh
 * @since 2018-04-21
 */
@Service
public class BlockServiceImpl extends ServiceImpl<BlockMapper, Block> implements IBlockService {
	
}
