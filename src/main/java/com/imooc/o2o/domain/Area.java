package com.imooc.o2o.domain;

import java.util.Date;

/**
 * 实体类：区域
 */
public class Area {
	private Integer areaId;// id
	private String areaName;// 名称
	private Integer priority;// 权重
	private Date creatTime;// 创建时间
	private Date lastEditTime;// 更新时间

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public Area(Integer areaId, String areaName, Integer priority, Date creatTime, Date lastEditTime) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.priority = priority;
		this.creatTime = creatTime;
		this.lastEditTime = lastEditTime;
	}

	public Area() {
		super();
	}

	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", areaName=" + areaName + ", priority=" + priority + ", creatTime="
				+ creatTime + ", lastEditTime=" + lastEditTime + "]";
	}

}
