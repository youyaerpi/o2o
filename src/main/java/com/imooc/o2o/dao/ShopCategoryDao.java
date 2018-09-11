package com.imooc.o2o.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.domain.ShopCategory;

public interface ShopCategoryDao {
	//@Param("shopCategoryCondition:添加制约条件
	List<ShopCategory> queyShopCategoryList(@Param("shopCategoryCondition")ShopCategory shopCategory);

}
