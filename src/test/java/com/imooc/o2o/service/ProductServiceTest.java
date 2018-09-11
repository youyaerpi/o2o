package com.imooc.o2o.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.domain.Product;
import com.imooc.o2o.domain.ProductCategory;
import com.imooc.o2o.domain.Shop;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.enums.ProductStateEnum;

public class ProductServiceTest extends BaseTest {
	@Autowired
	private ProductService productService;

	@Test
	public void testAddProduct() {
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(20L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(20L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("测试商品1");
		product.setProductDesc("测试商品1");
		product.setPriority(20);

		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		String file = "‪D:\\images\\coffee.jpg";
		String transferFile = file.replace("\\\\", "/");

		try {
			InputStream is = new FileInputStream(new File(transferFile));
			ImageHolder thumbnail = new ImageHolder(new File(transferFile).getName(), is);
			File productImg1 = new File("‪D:\\images\\coffee.jpg");
			InputStream is1 = new FileInputStream(productImg1);
			File productImg2 = new File("‪D:\\images\\liuyifei.jpg");
			InputStream is2 = new FileInputStream(productImg1);
			List<ImageHolder> productImgList = new ArrayList<ImageHolder>();

			productImgList.add(new ImageHolder(productImg1.getName(), is1));
			productImgList.add(new ImageHolder(productImg2.getName(), is2));
			ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
			assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
