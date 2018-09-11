package com.imooc.o2o.dao;

import java.util.List;

import com.imooc.o2o.domain.Area;

public interface AreaDao {
	/**
	 * 列出区域列表，
	 * @return
	 */
	List<Area> queryArea();

}
