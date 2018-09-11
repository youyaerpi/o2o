package com.imooc.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.domain.ProductCategory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {

	@Autowired 
	private ProductCategoryDao productCategoryDao;

	@Test
	public void testBQueryByShopId() throws Exception {
		long shopId = 15L;
		List<ProductCategory> list = productCategoryDao.queryProductCategoryList(shopId);
		System.out.println(list.size());
	}

	@Test
	public void testABatchInsertProductCategory() {
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryName("测试商品类别1");
		pc1.setPriority(2);
		pc1.setCreateTime(new Date());
		pc1.setShopId(17L);
		ProductCategory pc2 = new ProductCategory();
		pc2.setProductCategoryName("测试商品类别2");
		pc2.setPriority(4);
		pc2.setCreateTime(new Date());
		pc2.setShopId(17L);
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		list.add(pc1);
		list.add(pc2);
		int num = productCategoryDao.batchInsertProductCategory(list);
		System.out.println(list.size());
		System.out.println(num);
	}

	@Test
	public void testCDeleteProductCategory() {
		long shopId = 17L;
		List<ProductCategory> list = productCategoryDao.queryProductCategoryList(shopId);
		for (ProductCategory pc : list) {
			if ("测试商品类别2".equals(pc.getProductCategoryName())) {
				int num = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(), shopId);
				System.out.println(num);

			}
		}
	}

}
