package com.imooc.o2o.dao;

import java.util.List;

import com.imooc.o2o.domain.ProductImg;

public interface ProductImgDao {

	List<ProductImg> queryProductImgList(long productId);

	// 批量添加商品详情图片
	int batchInsertProductImg(List<ProductImg> productImgList);

	/**
	 * 删除指定商品下的所有
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
}
