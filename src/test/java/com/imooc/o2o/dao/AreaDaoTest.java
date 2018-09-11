package com.imooc.o2o.dao;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.domain.Area;

public class AreaDaoTest extends BaseTest {
/**
 * 测试查询
 */
	@Autowired
	private AreaDao areaDao;
	
	
		
	
	
	@Test
	public void testQueryArea() {
		List<Area> list = areaDao.queryArea();
		
		for (Area area : list) {
			System.out.println(area);
		}
		
		
	}

}
