package com.imooc.o2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.domain.Shop;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional // 添加事务
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
		// 判断shop是否为空，返回枚举的信息
		if (shop == null) {
			// NULL_SHOP_INFO(-1003, "传入了空的信息");
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);

		}
		/**
		 * 插入店铺
		 */
		try {
			// 店铺状态设为0：店铺未上架
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 影响的行数
			int effectNum = shopDao.insertShop(shop);
			if (effectNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
				if (thumbnail.getImage() != null) {
					// 存储图片
					try {
						addShopImg(shop, thumbnail);

					} catch (Exception e) {
						throw new ShopOperationException("shopImg error" + e.getMessage());

					}
					// 更新店铺的图片地址:更新影响的行数
					effectNum = shopDao.updateShop(shop);
					if (effectNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		} catch (Exception e) {
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public Shop getShopInfoById(long shopId) {
		return shopDao.queryByShopId(shopId);

	}

	@Override
	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);

		} else {
			try {
				// 1.判断是否需要处理图片
				if (thumbnail.getImage() != null ) {
					Shop tempshop = shopDao.queryByShopId(shop.getShopId());
					System.out.println(tempshop);
					System.out.println(tempshop);
					if (tempshop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempshop.getShopImg());
					}
					addShopImg(shop, thumbnail);
				}
				// 2.更新店铺信息
				shop.setLastEditTime(new Date());
				int effectNum = shopDao.updateShop(shop);
				if (effectNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {
					shop = shopDao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			} catch (ShopOperationException e) {
				throw new ShopOperationException("modifyShop error:" + e.getMessage());
			}

		}

	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if (shopList != null) {
			se.setShopList(shopList);
			se.setCount(count);
		} else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}
}
