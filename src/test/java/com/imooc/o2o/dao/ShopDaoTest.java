package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.domain.Area;
import com.imooc.o2o.domain.PersonInfo;
import com.imooc.o2o.domain.Shop;
import com.imooc.o2o.domain.ShopCategory;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testQueryShopList() throws Exception {
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(8L);
		shopCondition.setOwner(owner);
		List<Shop> queryShopList = shopDao.queryShopList(shopCondition, 0, 6);
		int queryShopCount = shopDao.queryShopCount(shopCondition);
		System.out.println(queryShopList.size());
		System.out.println(queryShopCount);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(18L);
		shopCondition.setShopCategory(sc);
		List<Shop> queryShopList2 = shopDao.queryShopList(shopCondition, 0, 2);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println(queryShopList2.size());
		System.out.println(count);
	}

	@Test
	public void testQueryByShopId() throws Exception {
		long shopId = 15L;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println(shop);
	}

	@Test
	public void testInsertShop() throws Exception {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(8L);
		area.setAreaId(3);
		shopCategory.setShopCategoryId(18L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺5");
		shop.setShopDesc("测试5");
		shop.setShopAddr("南昌5");
		shop.setPhone("124335555");
		shop.setShopImg("测试5");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectNum = shopDao.insertShop(shop);
		assertEquals(1, effectNum);

	}

	@Test
	public void testUpdateShop() throws Exception {
		Shop shop = new Shop();
		shop.setShopId(28L);
		shop.setShopDesc("测试");
		shop.setShopAddr("测试：赣州");
		shop.setLastEditTime(new Date());
		int effectNum = shopDao.updateShop(shop);
		System.out.println(effectNum);

	}
	@Test
	public void testQueryShopListAndCOUNT() throws Exception {
	Shop shopCondition=new Shop();
	ShopCategory childCategory=new ShopCategory();
	ShopCategory parentCategory=new ShopCategory();
	parentCategory.setShopCategoryId(12L);
	childCategory.setParent(parentCategory);
	shopCondition.setShopCategory(childCategory);
	List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 4);
	int count = shopDao.queryShopCount(shopCondition);
	System.out.println(shopList.size());
	System.out.println(count);
	}
}
