package com.imooc.o2o.service;

import java.util.List;

import com.imooc.o2o.domain.ShopCategory;

public interface ShopCategoryService {
	/**
	 * 根据查询条件获取到ShopCategory列表
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);

}
