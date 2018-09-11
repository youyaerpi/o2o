package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.domain.HeadLine;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HeadLineDaoTest extends BaseTest {
	@Autowired
	private HeadLineDao headLineDao;
	@Test
	//测试完毕
	public void testBQueryHeadLine() throws Exception {
		List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
		assertEquals(4, headLineList.size());
	}
}
