package com.imooc.o2o.service;

import java.util.List;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.domain.ProductCategory;

public interface ProductCategoryService {
	/**
	 * 查询指定某个店铺下的所有商品类别信息
	 * 
	 * @param long shopId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> getProductCategoryList(long shopId);

	/**
	 * 
	 * @param productCategoryList
	 * @return
	 * @throws ProductOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(
			List<ProductCategory> productCategoryList) throws ProductOperationException;

	/**
	 * 将此类别下的商品里的类别id置为空，在删掉该商品类别   
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,
			long shopId) throws ProductOperationException;
	
}
