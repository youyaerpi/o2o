package com.imooc.o2o.service;

import java.util.List;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.domain.Product;

public interface ProductService {

	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException;

	/**
	 * 通过商品id查询唯一的商品信息
	 * 
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId)throws ProductOperationException;

	/**
	 * 修改商品信息及图片处理
	 * 
	 * @param product
	 * @param thumbnail
	 * @param productImageHolderList
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImageHolderList)
			throws ProductOperationException;
	/**
	 * 查询商品列表并分页，可输入的条件：商品名（模糊），商品状态，店铺id,商品类别。
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition,int pageIndex,int pageSize);
}
