package com.imooc.o2o.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.imooc.o2o.BaseTest;
import com.imooc.o2o.domain.Area;
import com.imooc.o2o.domain.PersonInfo;
import com.imooc.o2o.domain.Shop;
import com.imooc.o2o.domain.ShopCategory;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.enums.ShopStateEnum;
public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;
	@Test
	public void testGetShopList(){
		Shop shopCondition=new Shop();
		ShopCategory sc=new ShopCategory();
		sc.setShopCategoryId(18L);
		shopCondition.setShopCategory(sc);
		ShopExecution se=shopService.getShopList(shopCondition, 4, 2);
		System.out.println("店铺列表数"+se.getShopList().size());
		System.out.println("店铺总数为"+se.getCount());
	}
	@Test
	public void testModifyShop() throws FileNotFoundException{
		Shop shop= new Shop();
		shop.setShopId(15L);
		shop.setPhone("1234567");
		File shopImg=new File("D:\\images\\xiaoshenke.jpg");
		InputStream is=new FileInputStream(shopImg);
		ImageHolder imageHolder=new ImageHolder("xiaoshenke.jpg", is);
		ShopExecution modifyShop = shopService.modifyShop(shop, imageHolder);
		System.out.println(modifyShop.getShop().getShopImg());
		
	}
	@Test
	public void testAddShop() throws FileNotFoundException{
		Shop shop =new Shop();
		PersonInfo owner =new PersonInfo();
		Area area= new Area();
		ShopCategory shopCategory=new ShopCategory();
		owner.setUserId(8L);
		area.setAreaId(3);
		shopCategory.setShopCategoryId(18L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺1");
		shop.setShopDesc("测试1");
		shop.setShopAddr("南昌1");
		shop.setPhone("1243351");
		shop.setPriority(1);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg=new File("D:\\images\\liuyifei.jpg");
		InputStream is=new FileInputStream(shopImg);
		ImageHolder imageHolder=new ImageHolder(shopImg.getName(),is);
		ShopExecution se = shopService.addShop(shop,imageHolder);
		assertEquals(ShopStateEnum.CHECK.getState(),se.getState()) ;
		
	}
 
}
