package com.imooc.o2o.service;
/**
 * sevice层测试
 */
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.imooc.o2o.BaseTest;
import com.imooc.o2o.domain.Area;
public class AreaServiceTest extends BaseTest {
	@Autowired
	private AreaService areaSevice;
	@Test
	public void testGetAreaList(){
		List<Area> areaList = areaSevice.getAreaList();
		assertEquals("交大北区",areaList.get(0).getAreaName());
		System.out.println(areaList.get(0).getAreaName());
	}

}
