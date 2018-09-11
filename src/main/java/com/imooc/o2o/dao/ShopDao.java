package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.domain.Shop;

public interface ShopDao {
	/**
	 * 返回queryShopList总数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	/**
	 * 分页查询店铺，可输入的条件有：店铺名（模糊），店铺状态。店铺类别。区域id，owner
	 * @param shopCondition
	 * @param rowIndex:从第几行开始取数据
	 * @param pageSize： 返回的行数
	 * @return
	 */
	List<Shop>queryShopList(@Param("shopCondition")Shop shopCondition,@Param("rowIndex")int rowIndex,
			@Param("pageSize")int pageSize);
	/**
	 * 通过shopId查询店铺
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);
	// 新增店铺
	int insertShop(Shop shop);

	// 更新店铺信息
	int updateShop(Shop shop);

}
