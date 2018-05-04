package com.gzeh.forum.bean;

import com.baomidou.mybatisplus.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author gzh
 * @since 2018-04-21
 */
@TableName("tbl_block")
public class Block extends Model<Block> {

    private static final long serialVersionUID = 1L;
    
    @JsonSerialize(using=ToStringSerializer.class)
	@TableId(value="bl_id",type=IdType.INPUT)
	private Long blId;
	@TableField("bl_name")
	private String blName;
	@TableField("bl_parent")
	private Integer blParent;
    /**
     * 是否删除 0：未删除 1：已删除
     */
	@TableField("bl_isdelete")
	private Integer blIsdelete;
    /**
     * 创建时间
     */
	@TableField("bl_date")
	private Date blDate;
    /**
     * 版主
     */
	@TableField("bl_manager")
	private Long blManager;
	
	
	@TableField("bl_icon")
	private String blIcon;


	
	
	public String getBlIcon() {
		return blIcon;
	}

	public void setBlIcon(String blIcon) {
		this.blIcon = blIcon;
	}

	public Long getBlId() {
		return blId;
	}

	public void setBlId(Long blId) {
		this.blId = blId;
	}

	public String getBlName() {
		return blName;
	}

	public void setBlName(String blName) {
		this.blName = blName;
	}

	public Integer getBlParent() {
		return blParent;
	}

	public void setBlParent(Integer blParent) {
		this.blParent = blParent;
	}

	public Integer getBlIsdelete() {
		return blIsdelete;
	}

	public void setBlIsdelete(Integer blIsdelete) {
		this.blIsdelete = blIsdelete;
	}

	public Date getBlDate() {
		return blDate;
	}

	public void setBlDate(Date blDate) {
		this.blDate = blDate;
	}

	public Long getBlManager() {
		return blManager;
	}

	public void setBlManager(Long blManager) {
		this.blManager = blManager;
	}

	@Override
	protected Serializable pkVal() {
		return this.blId;
	}

}
