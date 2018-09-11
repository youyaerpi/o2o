package com.imooc.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.domain.Product;
import com.imooc.o2o.domain.ProductImg;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	@Transactional
	// 处理缩略图，获取缩略图相对路径并赋值给product
	// 网tb_product写入商品信息，获取productId
	// 结合product批量处理商品详情图
	// 将商品详情图批量插入tb_product_img中
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException {
		// 空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// 给商品设置默认商品属性
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			// 设置默认的上架状态
			product.setEnableStatus(1);
			// 如果商品缩略图不为空则添加
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				// 创建商品信息
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品失败");

				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品失败" + e.toString());

			}
			// 若商品详情图不为空则添加
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS);
			// 传参为空则返回空值错误信息
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}

	}

	/**
	 * 批量添加图片
	 * 
	 * @param product
	 * @param productImgHolderList
	 */
	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		// 获取图片存储路径，这里直接存放到相应店铺的文件夹地下
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		// 遍历图片一次去处理，添加到productImg实体类
		for (ImageHolder productImgHolder : productImgHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		// 如果确实是有图片需要添加的，就执行批量添加操作
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品详情图片失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品详情图片失败" + e.toString());
			}
		}
	}

	/**
	 * 添加缩略图到数据库
	 * 
	 * @param product
	 * @param thumbnail
	 */
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);

	}

	@Override
	public Product getProductById(long productId) throws ProductOperationException {
		return productDao.queryProductById(productId);

	}

	@Override
	@Transactional
	/**
	 * 1.若缩略图参数有值，则处理缩略图 2.若原先存在缩略图则先删除再添加新图，之后获取缩略图的相对路径并赋值给product
	 * 3.若商品详情图列表参数有值，对商品详情图列表进行同样操作 4.将tb_product_img下面的该商品原先的商品详情图记录全部清除
	 * 5.更新tb_product，tb_product_img的信息 原则：有先删除，没有直接添加
	 * 先做逻辑图片的删除，再做物理图片的删除，即先删除图片在数据库的值，再删除再硬盘上的图片
	 */

	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImageHolderList) throws ProductOperationException {
		// 空值判断,不为空执行默认赋值
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			// 给商品设置上默认属性
			product.setLastEditTime(new Date());
			// 若商品缩略图不为空且原有缩略图不为空，则删除原有缩略图并添加
			if (thumbnail != null) {
				// 先获取一边原有信息，因为原来的信息里有原图片地址
				Product tempProduct = productDao.queryProductById(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				// 将缩略图加进去 thumbnail：缩略图
				addThumbnail(product, thumbnail);
			}
			// 如果有新添加的商品详情图，则将原先的删除，并添加新的图片
			if (productImageHolderList != null && productImageHolderList.size() > 0) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product, productImageHolderList);
			}
			try {
				// 更新商品信息
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("更新商品信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new ProductOperationException("更新商品信息失败" + e.getMessage());
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}

	}

	/**
	 * 删除某个商品下的所有详情图
	 * 
	 * @param productId
	 */
	private void deleteProductImgList(long productId) {
		// 根据productId获取到原来的详情图片列表图片
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		// 循环遍历删除图片（物理删除）
		for (ProductImg productImg : productImgList) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		// 删除数据库里原有的图片信息(逻辑删除)
		productImgDao.deleteProductImgByProductId(productId);

	}

	@Override

	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;

	}
}
