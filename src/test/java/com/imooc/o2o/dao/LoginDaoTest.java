package com.imooc.o2o.dao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.imooc.o2o.BaseTest;
import com.imooc.o2o.domain.PersonInfo;

public class LoginDaoTest extends BaseTest {
/**
 * 测试查询
 */
	@Autowired
	private LoginDao loginDao;
	@Test
	public void testDoLogin(){
		PersonInfo personInfo=new PersonInfo();
	   String name =personInfo.setName("king");
		
		PersonInfo user = loginDao.queryShopByUsername(name);
		System.out.println(user);
	}
}
