package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.domain.ShopCategory;

public class ShopCategoryTest extends BaseTest {
	@Autowired
	private ShopCategoryDao ShopCategoryDao;

	@Test
	public void queyShopCategoryList() throws Exception {
		List<ShopCategory> list=ShopCategoryDao.queyShopCategoryList(null);
		System.out.println(list.size());
	

}
}
